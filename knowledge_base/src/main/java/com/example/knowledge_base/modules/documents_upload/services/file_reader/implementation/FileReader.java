package com.example.knowledge_base.modules.documents_upload.services.file_reader.implementation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.knowledge_base.modules.documents_upload.exceptions.FileUploadException;
import com.example.knowledge_base.modules.documents_upload.services.file_reader.IFileReader;

@Service
public class FileReader implements IFileReader {

    @Override
    public String read(MultipartFile file) {
        try {
            String fileContent = StreamUtils.copyToString(file.getInputStream(), StandardCharsets.UTF_8);
            return fileContent;
        } catch (IOException e) {
            throw new FileUploadException("Failed to process file content", e);
        }
    }

}
