package com.example.knowledge_base.modules.documents_upload.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FileRecord {
    private String filePath;
    private String mimeType;
    private String fileSize;
    private byte[] fileContent;
}
