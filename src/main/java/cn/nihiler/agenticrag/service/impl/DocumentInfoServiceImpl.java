package cn.nihiler.agenticrag.service.impl;

import cn.nihiler.agenticrag.entity.Document;
import cn.nihiler.agenticrag.mapper.DocumentMapper;
import cn.nihiler.agenticrag.service.DocumentInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文档信息服务实现
 */
@Service
public class DocumentInfoServiceImpl extends ServiceImpl<DocumentMapper, Document>
        implements DocumentInfoService {

    @Override
    public Document getByDocumentId(String documentId) {
        return this.getOne(new LambdaQueryWrapper<Document>()
                .eq(Document::getDocumentId, documentId));
    }

    @Override
    public void updateStatus(String documentId, String status) {
        this.update(new LambdaUpdateWrapper<Document>()
                .eq(Document::getDocumentId, documentId)
                .set(Document::getStatus, status));
    }
}

