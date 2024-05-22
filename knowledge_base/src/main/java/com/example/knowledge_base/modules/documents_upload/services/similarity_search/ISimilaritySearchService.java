package com.example.knowledge_base.modules.documents_upload.services.similarity_search;

import java.util.List;

import org.springframework.ai.document.Document;

public interface ISimilaritySearchService {
    List<Document> similaritySearch(String query);

    List<Document> similaritySearch(String query, int limit);
}
