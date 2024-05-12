package com.example.knowledge_base.modules.documents_upload.exceptions;

import com.example.knowledge_base.shared.exceptions.ServerException;

public class FileUploadException extends ServerException {

    public FileUploadException(String message, Exception exception) {
        super("FILE_UPLOAD_FAILED", message, exception);
    }

}
