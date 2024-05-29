package com.example.knowledge_base.upload;

import com.example.knowledge_base.modules.documents_upload.entities.FileRecord;
import com.example.knowledge_base.modules.documents_upload.exceptions.FileNotFoundException;
import com.example.knowledge_base.modules.documents_upload.repository.file_upload.IFileUploadRepository;
import com.example.knowledge_base.modules.documents_upload.services.file_upload.implementation.FileUploadService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FileUploadServiceTest {

    @Mock
    private IFileUploadRepository fileUploadRepository;

    @InjectMocks
    private FileUploadService fileUploadService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddFile() {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "File content".getBytes());
        when(fileUploadRepository.addFile(file)).thenReturn("file-id");

        String result = fileUploadService.addFile(file);

        assertEquals("file-id", result);
    }

    @Test
    public void testGetFile() {
        byte[] content = "File content".getBytes();
        FileRecord fileRecord = new FileRecord("66578818e23a9651c9e33340", "test.txt", "text/plain",
                (long) content.length, content);
        when(fileUploadRepository.downloadFile("file-id")).thenReturn(Optional.of(fileRecord));

        FileRecord result = fileUploadService.getFile("file-id");

        assertEquals(fileRecord, result);
    }

    @Test
    public void testGetFileNotFound() {
        when(fileUploadRepository.downloadFile("file-id")).thenReturn(Optional.empty());

        assertThrows(FileNotFoundException.class, () -> fileUploadService.getFile("file-id"));
    }

    @Test
    public void testGetAll() {
        byte[] content = "File content".getBytes();

        List<FileRecord> fileRecords = List
                .of(new FileRecord("66578818e23a9651c9e33340", "test.txt", "text/plain", (long) content.length,
                        content));
        when(fileUploadRepository.findAll()).thenReturn(fileRecords);

        List<FileRecord> result = fileUploadService.getAll();

        assertEquals(fileRecords, result);
    }

    @Test
    public void testDeleteById() {
        when(fileUploadRepository.deleteById("file-id")).thenReturn(true);

        boolean result = fileUploadService.deleteById("file-id");

        assertTrue(result);
    }

    @Test
    public void testDeleteByIdNotFound() {
        when(fileUploadRepository.deleteById("file-id")).thenReturn(false);

        boolean result = fileUploadService.deleteById("file-id");

        assertFalse(result);
    }
}
