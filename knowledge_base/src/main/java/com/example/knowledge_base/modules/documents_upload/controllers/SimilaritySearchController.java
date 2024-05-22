package com.example.knowledge_base.modules.documents_upload.controllers;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.knowledge_base.modules.documents_upload.dto.DocumentResponseDto;
import com.example.knowledge_base.modules.documents_upload.mapper.DocumentMapper;
import com.example.knowledge_base.modules.documents_upload.services.similarity_search.ISimilaritySearchService;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SimilaritySearchController {
    private final ISimilaritySearchService similaritySearchService;
    private final DocumentMapper documentMapper;

    @GetMapping
    public ResponseEntity<List<DocumentResponseDto>> similaritySearch(
            @NotNull @RequestParam @Size(max = 10000, message = "Query length must be less than or equal to 10000") String query,
            @RequestParam(required = false, defaultValue = "10") @Min(1) @Max(Integer.MAX_VALUE) int limit) {

        List<Document> result = similaritySearchService.similaritySearch(query, limit);

        return ResponseEntity.ok(result.stream().map(documentMapper::toDto).toList());
    }
}
