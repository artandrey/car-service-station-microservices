package com.example.knowledge_base.upload;

import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;

import com.example.knowledge_base.modules.documents_upload.services.document_splitter.IDocumentSplitter;
import com.example.knowledge_base.modules.documents_upload.services.document_splitter.implementation.DocumentSplitter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentSplitterTest {

    private final IDocumentSplitter documentSplitter = new DocumentSplitter();

    @Test
    public void testSplit() {
        String document = "Line 1\nLine 2\nLine 3";
        List<Document> documents = documentSplitter.split(document);

        assertEquals(3, documents.size());
        assertEquals("Line 1", documents.get(0).getContent());
        assertEquals("Line 2", documents.get(1).getContent());
        assertEquals("Line 3", documents.get(2).getContent());
    }
}
