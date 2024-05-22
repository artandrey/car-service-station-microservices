package com.example.knowledge_base.modules.documents_upload.controllers;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.knowledge_base.modules.documents_upload.entities.FileRecord;
import com.example.knowledge_base.modules.documents_upload.exceptions.UnsupportedFileFormatException;
import com.example.knowledge_base.modules.documents_upload.services.documents_upload.IDocumentsUploadService;
import com.example.knowledge_base.modules.documents_upload.services.file_upload.IFileUploadService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileUploadController {
    private final IFileUploadService fileUploadService;
    private final IDocumentsUploadService documentsUploadService;

    @GetMapping()
    public ResponseEntity<List<FileRecord>> getAll() {
        return ResponseEntity.ok(fileUploadService.getAll());
    }

    @PostMapping()
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {

        if (file.getContentType() == null || !file.getContentType().equals("text/plain")) {
            throw new UnsupportedFileFormatException();
        }

        return new ResponseEntity<>(documentsUploadService.upload(file), HttpStatus.OK);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) {
        FileRecord fileRecord = fileUploadService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileRecord.getMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileRecord.getFilePath() + "\"")
                .body(new ByteArrayResource(fileRecord.getFileContent()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        documentsUploadService.delete(id);
        return ResponseEntity.accepted().build();
    }
}
