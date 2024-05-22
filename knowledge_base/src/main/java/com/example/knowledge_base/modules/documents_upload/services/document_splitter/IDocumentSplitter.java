package com.example.knowledge_base.modules.documents_upload.services.document_splitter;

import java.util.List;

import org.springframework.ai.document.Document;

public interface IDocumentSplitter {
    List<Document> split(String document);
}
