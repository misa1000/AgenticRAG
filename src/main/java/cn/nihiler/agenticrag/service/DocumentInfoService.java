package cn.nihiler.agenticrag.service;

import cn.nihiler.agenticrag.entity.Document;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 文档信息服务接口（数据库CRUD）
 */
public interface DocumentInfoService extends IService<Document> {

    /**
     * 根据文档ID查询
     *
     * @param documentId 文档唯一标识
     * @return 文档实体
     */
    Document getByDocumentId(String documentId);

    /**
     * 更新文档状态
     *
     * @param documentId 文档ID
     * @param status     状态
     */
    void updateStatus(String documentId, String status);
}

