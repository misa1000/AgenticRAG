package cn.nihiler.agenticrag.agent.router;

/**
 * 路由类型枚举
 * 
 * 定义Agent可以选择的不同处理路径
 */
public enum RouteType {

    /**
     * 向量检索 - 查询历史文档/知识库
     */
    VECTOR_STORE,

    /**
     * SQL查询 - 查询结构化业务数据
     */
    SQL_DATABASE,

    /**
     * Web搜索 - 查询即时新闻/实时信息
     */
    WEB_SEARCH,

    /**
     * 直接回答 - 简单问候/闲聊，无需检索
     */
    DIRECT_ANSWER
}


