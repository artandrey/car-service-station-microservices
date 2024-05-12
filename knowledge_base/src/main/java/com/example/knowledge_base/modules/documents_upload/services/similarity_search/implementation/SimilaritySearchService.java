package com.example.knowledge_base.modules.documents_upload.services.similarity_search.implementation;

import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;

import com.example.knowledge_base.modules.documents_upload.services.similarity_search.ISimilaritySearchService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimilaritySearchService implements ISimilaritySearchService {
    private final VectorStore vectorStore;

    public List<Document> similaritySearch(String query, int limit) {
        return vectorStore.similaritySearch(SearchRequest.defaults().withQuery(query).withTopK(limit));
    }

    @Override
    public List<Document> similaritySearch(String query) {
        return similaritySearch(query, SearchRequest.DEFAULT_TOP_K);
    }
}
