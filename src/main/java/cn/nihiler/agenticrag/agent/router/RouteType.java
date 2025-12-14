package cn.nihiler.agenticrag.agent.router;

/**
 * 路由类型枚举
 * 
 * 定义Agent可以选择的不同处理路径
 */
public enum RouteType {

    /**
     * FAQ匹配 - 命中预设的常见问题
     */
    FAQ,

    /**
     * 向量检索/RAG - 查询历史文档/知识库
     */
    RAG,

    /**
     * SQL查询 - 查询结构化业务数据
     */
    DATABASE,

    /**
     * Web搜索 - 查询即时新闻/实时信息
     */
    SEARCH,

    /**
     * 直接回答 - 简单问候/闲聊，无需检索
     */
    DIRECT
}
