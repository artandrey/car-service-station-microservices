package com.example.ai_assistant.modules.ai_response.services.knowledge_base;

import java.util.List;

import com.example.ai_assistant.modules.ai_response.models.Document;

public interface IKnowledgeBaseService {
    List<Document> search(String query);

    List<Document> search(String query, Integer limit);

}
