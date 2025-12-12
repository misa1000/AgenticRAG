package cn.nihiler.agenticrag.service.impl;

import cn.nihiler.agenticrag.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * 文档服务实现
 * 
 * TODO: 在这里实现文档的解析、分块、向量化、存储到VectorStore
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    // TODO: 注入 VectorStore 和 EmbeddingModel
    // private final VectorStore vectorStore;
    // private final EmbeddingModel embeddingModel;

    @Override
    public String uploadAndIndex(MultipartFile file) {
        log.info("上传文档: {}", file.getOriginalFilename());

        String documentId = UUID.randomUUID().toString();

        // TODO: 实现文档处理流程
        // 1. 使用 Tika 解析文档内容
        // 2. 分块 (Chunking)
        // 3. 向量化 (Embedding)
        // 4. 存储到 VectorStore

        return documentId;
    }

    @Override
    public void deleteDocument(String documentId) {
        log.info("删除文档: {}", documentId);
        // TODO: 从 VectorStore 中删除文档
    }
}

