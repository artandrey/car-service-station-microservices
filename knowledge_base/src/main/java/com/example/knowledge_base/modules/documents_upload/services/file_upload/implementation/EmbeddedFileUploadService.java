package com.example.knowledge_base.modules.documents_upload.services.file_upload.implementation;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.knowledge_base.modules.documents_upload.entities.FileRecord;
import com.example.knowledge_base.modules.documents_upload.exceptions.FileNotFoundException;
import com.example.knowledge_base.modules.documents_upload.repository.file_upload.IFileUploadRepository;
import com.example.knowledge_base.modules.documents_upload.services.file_upload.IEmbeddedFileUploadService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EmbeddedFileUploadService implements IEmbeddedFileUploadService {

    private final IFileUploadRepository fileUploadRepository;

    @Override
    public String addFile(MultipartFile upload) {
        return fileUploadRepository.addFile(upload);
    }

    @Override
    public FileRecord getFile(String id) {
        FileRecord fileRecord = fileUploadRepository.downloadFile(id).orElseThrow(() -> new FileNotFoundException(id));
        return fileRecord;
    }

    @Override
    public List<FileRecord> getAll() {
        return fileUploadRepository.findAll();
    }

}
