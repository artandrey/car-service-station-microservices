package com.example.knowledge_base.modules.documents_upload.services.documents_upload;

import org.springframework.web.multipart.MultipartFile;

public interface IDocumentsUploadService {
    String upload(MultipartFile upload);

    boolean delete(String id);
}
