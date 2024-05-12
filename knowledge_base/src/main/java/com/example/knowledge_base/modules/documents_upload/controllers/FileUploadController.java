package com.example.knowledge_base.modules.documents_upload.controllers;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.knowledge_base.modules.documents_upload.entities.FileRecord;
import com.example.knowledge_base.modules.documents_upload.exceptions.UnsupportedFileFormatException;
import com.example.knowledge_base.modules.documents_upload.services.file_upload.IFileUploadService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileUploadController {
    private final IFileUploadService fileUploadService;

    @GetMapping()
    public ResponseEntity<List<FileRecord>> getAll() {
        return ResponseEntity.ok(fileUploadService.getAll());
    }

    @PostMapping()
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.getContentType() != "text/plain") {
            throw new UnsupportedFileFormatException();
        }

        return new ResponseEntity<>(fileUploadService.addFile(file), HttpStatus.OK);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) {
        FileRecord fileRecord = fileUploadService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileRecord.getMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileRecord.getFilePath() + "\"")
                .body(new ByteArrayResource(fileRecord.getFileContent()));
    }
}
