-- ==================== 创建数据库 ====================
CREATE DATABASE IF NOT EXISTS agentic_rag DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE agentic_rag;

-- ==================== 聊天历史表 ====================
CREATE TABLE IF NOT EXISTS chat_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    session_id VARCHAR(64) NOT NULL COMMENT '会话ID',
    role VARCHAR(20) NOT NULL COMMENT '角色(user/assistant)',
    content TEXT NOT NULL COMMENT '消息内容',
    route_type VARCHAR(32) COMMENT '使用的路由类型',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除(0-未删除,1-已删除)',
    INDEX idx_session_id (session_id),
    INDEX idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='聊天历史表';

-- ==================== 文档表 ====================
CREATE TABLE IF NOT EXISTS document (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    document_id VARCHAR(64) NOT NULL UNIQUE COMMENT '文档唯一标识',
    name VARCHAR(255) NOT NULL COMMENT '文档名称',
    type VARCHAR(32) COMMENT '文档类型(pdf,docx,txt等)',
    size BIGINT COMMENT '文档大小(字节)',
    status VARCHAR(32) DEFAULT 'pending' COMMENT '文档状态(pending,indexed,failed)',
    chunk_count INT DEFAULT 0 COMMENT '分块数量',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '逻辑删除(0-未删除,1-已删除)',
    INDEX idx_document_id (document_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文档表';

