package com.example.knowledge_base.modules.documents_upload.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Component;

import com.example.knowledge_base.modules.documents_upload.dto.DocumentResponseDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DocumentMapper {
    private final ModelMapper modelMapper;

    public DocumentResponseDto toDto(Document document) {
        DocumentResponseDto documentResponseDto = modelMapper.map(document, DocumentResponseDto.class);
        documentResponseDto.setContent(document.getContent());
        return documentResponseDto;
    }
}
