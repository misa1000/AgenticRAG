package cn.nihiler.agenticrag.agent.reflection;

import cn.nihiler.agenticrag.agent.retriever.RetrievedDocument;

import java.util.List;

/**
 * 自省器接口
 * 
 * 核心功能：检查检索到的内容是否能够回答用户的问题
 * 
 * TODO: 你需要实现这个接口，这是Agentic RAG的精髓：
 * 1. 让LLM评估检索结果的相关性
 * 2. 如果不相关，触发Query Rewrite（重写查询）
 * 3. 重新检索，直到满意或达到最大重试次数
 */
public interface SelfReflector {

    /**
     * 评估检索结果是否足够回答问题
     *
     * @param query     用户原始问题
     * @param documents 检索到的文档
     * @return 评估结果
     */
    ReflectionResult evaluate(String query, List<RetrievedDocument> documents);

    /**
     * 重写查询语句
     *
     * @param originalQuery 原始查询
     * @param context       上下文信息（之前检索的结果等）
     * @return 重写后的查询
     */
    String rewriteQuery(String originalQuery, String context);
}

