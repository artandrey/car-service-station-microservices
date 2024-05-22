package com.example.ai_assistant.modules.ai_response.services.knowledge_base.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.ai_assistant.modules.ai_response.models.Document;
import com.example.ai_assistant.modules.ai_response.services.knowledge_base.IKnowledgeBaseService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class KnowledgeBaseService implements IKnowledgeBaseService {
    private final WebClient.Builder webClientBuilder;

    @Override
    public List<Document> search(String query) {
        return search(query, null);
    }

    @Override
    public List<Document> search(String query, Integer limit) {
        Document[] documents = webClientBuilder.baseUrl("lb://knowledge-base").build().get()
                .uri("/search", uriBuilder -> {
                    uriBuilder = uriBuilder.queryParam("query", query);
                    if (limit != null) {
                        uriBuilder = uriBuilder.queryParam("limit", limit);
                    }
                    return uriBuilder.build();
                })
                .retrieve().bodyToMono(Document[].class).block();
        return Arrays.stream(documents).toList();
    }

}
