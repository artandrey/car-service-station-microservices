package com.example.knowledge_base.modules.documents_upload.services.file_reader;

import org.springframework.web.multipart.MultipartFile;

public interface IFileReader {
    String read(MultipartFile file);
}
