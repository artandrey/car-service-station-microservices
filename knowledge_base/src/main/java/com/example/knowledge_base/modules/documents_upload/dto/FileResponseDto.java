package com.example.knowledge_base.modules.documents_upload.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileResponseDto {

    @Schema(description = "Path to the uploaded file", example = "myfile.txt")
    private String filePath;

    @Schema(description = "MIME type of the uploaded file", example = "plain/txt")
    private String mimeType;

    @Schema(description = "Size of the uploaded file in bytes", example = "1024")
    private String fileSize;

    @Schema(description = "Content of the uploaded file as a byte array")
    private byte[] fileContent;
}
