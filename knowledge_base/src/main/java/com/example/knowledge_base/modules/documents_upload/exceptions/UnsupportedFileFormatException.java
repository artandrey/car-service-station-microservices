package com.example.knowledge_base.modules.documents_upload.exceptions;

import com.example.knowledge_base.shared.exceptions.ClientException;

public class UnsupportedFileFormatException extends ClientException {

    public UnsupportedFileFormatException() {
        super("UNSUPPORTED_FILE_FORMAT", "Provided file format is not supported");
    }

}
