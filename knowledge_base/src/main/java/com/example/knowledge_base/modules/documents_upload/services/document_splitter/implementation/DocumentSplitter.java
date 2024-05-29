package com.example.knowledge_base.modules.documents_upload.services.document_splitter.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;

import com.example.knowledge_base.modules.documents_upload.services.document_splitter.IDocumentSplitter;

@Service
public class DocumentSplitter implements IDocumentSplitter {

    @Override
    public List<Document> split(String document) {

        return Arrays.stream(document.split("\n\n")).filter(content -> content != null)
                .map(content -> new Document(content)).toList();
    }

}
