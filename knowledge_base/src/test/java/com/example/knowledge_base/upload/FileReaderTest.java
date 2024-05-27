package com.example.knowledge_base.upload;

import com.example.knowledge_base.modules.documents_upload.services.file_reader.IFileReader;
import com.example.knowledge_base.modules.documents_upload.services.file_reader.implementation.FileReader;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileReaderTest {

    private final IFileReader fileReader = new FileReader();

    @Test
    public void testRead() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "File content".getBytes());
        String content = fileReader.read(file);
        assertEquals("File content", content);
    }

}