package com.example.knowledge_base.modules.documents_upload.mapper;

import org.springframework.stereotype.Component;

import com.example.knowledge_base.modules.documents_upload.entities.FileRecord;
import com.mongodb.client.gridfs.model.GridFSFile;

@Component
public class FileRecordMapper {
    public FileRecord toEntity(GridFSFile gridFSFile, byte[] fileContent) {
        FileRecord fileRecord = new FileRecord();

        toEntity(gridFSFile);
        fileRecord.setFileContent(fileContent);

        return fileRecord;
    }

    public FileRecord toEntity(GridFSFile gridFSFile) {
        FileRecord fileRecord = new FileRecord();

        if (gridFSFile.getMetadata() != null) {
            fileRecord.setFilePath(gridFSFile.getFilename());

            fileRecord.setMimeType(gridFSFile.getMetadata().get("_contentType").toString());

            fileRecord.setFileSize(gridFSFile.getMetadata().get("fileSize").toString());
        }

        return fileRecord;
    }
}
