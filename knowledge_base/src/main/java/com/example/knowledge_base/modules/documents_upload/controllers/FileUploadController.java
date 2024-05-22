package com.example.knowledge_base.modules.documents_upload.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.knowledge_base.modules.documents_upload.dto.FileResponseDto;
import com.example.knowledge_base.modules.documents_upload.entities.FileRecord;
import com.example.knowledge_base.modules.documents_upload.exceptions.UnsupportedFileFormatException;
import com.example.knowledge_base.modules.documents_upload.mapper.FileRecordMapper;
import com.example.knowledge_base.modules.documents_upload.services.documents_upload.IDocumentsUploadService;
import com.example.knowledge_base.modules.documents_upload.services.file_upload.IFileUploadService;
import lombok.AllArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileUploadController {
    private final IFileUploadService fileUploadService;
    private final IDocumentsUploadService documentsUploadService;
    private final FileRecordMapper fileRecordMapper;

    @GetMapping()
    @Operation(summary = "Get all files", description = "Retrieves a list of all files")
    public ResponseEntity<List<FileResponseDto>> getAll() {
        return ResponseEntity.ok(fileUploadService.getAll().stream().map(fileRecordMapper::toDto).toList());
    }

    @PostMapping()
    @Operation(summary = "Upload file", description = "Uploads a file")
    @ApiResponse(responseCode = "200", description = "File uploaded successfully")
    @ApiResponse(responseCode = "400", description = "Unsupported file format")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.getContentType() == null || !file.getContentType().equals("text/plain")) {
            throw new UnsupportedFileFormatException();
        }
        return new ResponseEntity<>(documentsUploadService.upload(file), HttpStatus.OK);
    }

    @GetMapping("/download/{id}")
    @Operation(summary = "Download file", description = "Downloads a file by ID")
    @ApiResponse(responseCode = "200", description = "File downloaded successfully", content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE, schema = @Schema(implementation = ByteArrayResource.class)))
    @ApiResponse(responseCode = "404", description = "File not found")
    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) {
        FileRecord fileRecord = fileUploadService.getFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileRecord.getMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileRecord.getFilePath() + "\"")
                .body(new ByteArrayResource(fileRecord.getFileContent()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete file", description = "Deletes a file by ID")
    @ApiResponse(responseCode = "202", description = "File deleted successfully")
    @ApiResponse(responseCode = "404", description = "File not found")
    public ResponseEntity<?> delete(@PathVariable String id) {
        documentsUploadService.delete(id);
        return ResponseEntity.accepted().build();
    }
}
