package com.example.knowledge_base.modules.documents_upload.repository.file_upload;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.knowledge_base.modules.documents_upload.entities.FileRecord;

public interface IFileUploadRepository {
    List<FileRecord> findAll();

    String addFile(MultipartFile upload);

    Optional<FileRecord> downloadFile(String id);
}
