package com.example.knowledge_base.search;

import com.example.knowledge_base.modules.documents_upload.services.similarity_search.implementation.SimilaritySearchService;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SimilaritySearchServiceTest {

    @Mock
    private VectorStore vectorStore;

    @InjectMocks
    private SimilaritySearchService similaritySearchService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSimilaritySearchWithLimit() {
        String query = "search query";
        int limit = 5;
        List<Document> documents = List.of(new Document("Document 1"), new Document("Document 2"));

        when(vectorStore.similaritySearch(SearchRequest.defaults().withQuery(query).withTopK(limit)))
                .thenReturn(documents);

        List<Document> result = similaritySearchService.similaritySearch(query, limit);

        assertEquals(documents, result);
    }

    @Test
    public void testSimilaritySearchDefaultLimit() {
        String query = "search query";
        List<Document> documents = List.of(new Document("Document 1"), new Document("Document 2"));

        when(vectorStore
                .similaritySearch(SearchRequest.defaults().withQuery(query).withTopK(SearchRequest.DEFAULT_TOP_K)))
                .thenReturn(documents);

        List<Document> result = similaritySearchService.similaritySearch(query);

        assertEquals(documents, result);
    }
}
