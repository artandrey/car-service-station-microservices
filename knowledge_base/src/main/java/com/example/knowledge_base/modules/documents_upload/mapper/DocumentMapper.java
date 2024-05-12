package com.example.knowledge_base.modules.documents_upload.mapper;

import org.springframework.ai.document.Document;

import com.example.knowledge_base.modules.documents_upload.dto.DocumentResponseDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DocumentMapper {

    public DocumentResponseDto toDto(Document document) {
        return new DocumentResponseDto(document.getContent());
    }
}
