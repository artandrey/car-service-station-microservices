package com.example.knowledge_base.modules.documents_upload.services.file_upload.implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.knowledge_base.modules.documents_upload.entities.FileRecord;
import com.example.knowledge_base.modules.documents_upload.exceptions.FileNotFoundException;
import com.example.knowledge_base.modules.documents_upload.repository.file_upload.IFileUploadRepository;
import com.example.knowledge_base.modules.documents_upload.services.file_upload.IFileUploadService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FileUploadService implements IFileUploadService {

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

    @Override
    public boolean deleteById(String id) {
        return fileUploadRepository.deleteById(id);
    }

}
