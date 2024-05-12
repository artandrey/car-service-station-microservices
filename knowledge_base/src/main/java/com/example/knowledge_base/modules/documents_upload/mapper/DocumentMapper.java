package com.example.knowledge_base.modules.documents_upload.mapper;

import org.springframework.ai.document.Document;
import org.springframework.stereotype.Component;

import com.example.knowledge_base.modules.documents_upload.dto.DocumentResponseDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DocumentMapper {

    public DocumentResponseDto toDto(Document document) {
        return new DocumentResponseDto(document.getContent());
    }
}
