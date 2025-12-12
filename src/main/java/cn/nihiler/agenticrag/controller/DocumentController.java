package cn.nihiler.agenticrag.controller;

import cn.nihiler.agenticrag.common.Result;
import cn.nihiler.agenticrag.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文档管理API控制器
 * 
 * 用于上传文档到向量库
 */
@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    /**
     * 上传文档并建立索引
     */
    @PostMapping("/upload")
    public Result<String> uploadDocument(@RequestParam("file") MultipartFile file) {
        String documentId = documentService.uploadAndIndex(file);
        return Result.success(documentId);
    }

    /**
     * 删除文档
     */
    @DeleteMapping("/{documentId}")
    public Result<Void> deleteDocument(@PathVariable String documentId) {
        documentService.deleteDocument(documentId);
        return Result.success();
    }
}

