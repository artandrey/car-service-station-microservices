package com.example.knowledge_base.modules.documents_upload.services.file_upload;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.knowledge_base.modules.documents_upload.entities.FileRecord;

public interface IFileUploadService {
    String addFile(MultipartFile upload);

    FileRecord getFile(String id);

    List<FileRecord> getAll();
}
