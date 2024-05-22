package com.example.knowledge_base.modules.documents_upload.repository.file_upload.implementation;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.apache.commons.io.IOUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.knowledge_base.modules.documents_upload.entities.FileRecord;
import com.example.knowledge_base.modules.documents_upload.exceptions.FileUploadException;
import com.example.knowledge_base.modules.documents_upload.mapper.FileRecordMapper;
import com.example.knowledge_base.modules.documents_upload.repository.file_upload.IFileUploadRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class FileUploadRepository implements IFileUploadRepository {

    private final GridFsTemplate template;

    private final GridFsOperations operations;

    private final FileRecordMapper fileRecordMapper;

    public List<FileRecord> findAll() {
        GridFSFindIterable gridFSFiles = template.find(new Query());
        return StreamSupport.stream(gridFSFiles.spliterator(), false).map(fileRecordMapper::toEntity).toList();
    }

    public String addFile(MultipartFile upload) {

        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", upload.getSize());

        Object fileID;
        try {
            fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(),
                    metadata);
            return fileID.toString();
        } catch (IOException e) {
            throw new FileUploadException("Failed to process file content", e);
        }

    }

    public boolean deleteById(String id) {
        operations.delete(new Query(Criteria.where("_id").is(id)));
        return true;
    }

    public Optional<FileRecord> downloadFile(String id) {

        GridFSFile gridFSFile = template.findOne(new Query(Criteria.where("_id").is(id)));

        if (!Optional.ofNullable(gridFSFile).isPresent()) {
            return Optional.ofNullable(null);
        }

        try {
            byte[] fileContent = IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream());
            return Optional.of(fileRecordMapper.toEntity(gridFSFile, fileContent));

        } catch (IllegalStateException | IOException e) {
            throw new FileUploadException("Failed to process file content", e);
        }

    }
}
