package cn.nihiler.agenticrag.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文档服务接口
 */
public interface DocumentService {

    /**
     * 上传文档并建立向量索引
     *
     * @param file 文档文件
     * @return 文档ID
     */
    String uploadAndIndex(MultipartFile file);

    /**
     * 删除文档及其向量索引
     *
     * @param documentId 文档ID
     */
    void deleteDocument(String documentId);
}

