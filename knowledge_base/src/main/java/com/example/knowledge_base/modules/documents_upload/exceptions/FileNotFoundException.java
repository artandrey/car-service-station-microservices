package com.example.knowledge_base.modules.documents_upload.exceptions;

import com.example.knowledge_base.shared.exceptions.NotFoundException;

public class FileNotFoundException extends NotFoundException {

    public FileNotFoundException(String fileId) {
        super("FILE_NOT_FOUND", "File with Id: " + fileId + " was not found");
    }

}
