package com.example.knowledge_base.modules.documents_upload.services.documents_upload.implementation;

import java.util.List;
import java.util.Map;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.knowledge_base.modules.documents_upload.services.document_splitter.IDocumentSplitter;
import com.example.knowledge_base.modules.documents_upload.services.documents_upload.IDocumentsUploadService;
import com.example.knowledge_base.modules.documents_upload.services.file_reader.IFileReader;
import com.example.knowledge_base.modules.documents_upload.services.file_upload.IFileUploadService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GridFsDocumentsUploadService implements IDocumentsUploadService {

    private final VectorStore vectorStore;
    private final IFileUploadService fileUploadService;
    private final IFileReader fileReader;
    private final IDocumentSplitter documentSplitter;

    @Override
    @Transactional
    public String upload(MultipartFile upload) {
        String fileId = fileUploadService.addFile(upload);
        String fileContent = fileReader.read(upload);
        List<Document> splittedContent = documentSplitter.split(fileContent);
        splittedContent.forEach(document -> {
            Map<String, Object> metadata = document.getMetadata();
            metadata.put("fileId", fileId);
        });

        vectorStore.add(splittedContent);

        return fileId;
    }

    @Override
    public boolean delete(String id) {
        return fileUploadService.deleteById(id);
    }

}
