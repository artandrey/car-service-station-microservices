package com.example.knowledge_base.modules.documents_upload.services.document_splitter.implementation;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import com.example.knowledge_base.modules.documents_upload.services.document_splitter.IDocumentSplitter;

@Service
public class DocumentSplitter implements IDocumentSplitter {

    @Override
    public List<Document> split(String document) {
        return Stream.of(document.split("\n")).map(Document::new).toList();
    }

}
