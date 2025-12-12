package cn.nihiler.agenticrag.agent.retriever;

import java.util.List;

/**
 * 文档检索器接口
 * 
 * TODO: 你需要实现这个接口，核心功能：
 * 1. 向量检索 - 从VectorStore检索相关文档
 * 2. 混合检索 - 结合BM25关键词检索 + 向量检索
 * 3. Re-ranking - 对检索结果进行重排序
 */
public interface DocumentRetriever {

    /**
     * 检索相关文档
     *
     * @param query 用户查询
     * @param topK  返回数量
     * @return 检索到的文档列表
     */
    List<RetrievedDocument> retrieve(String query, int topK);

    /**
     * 混合检索（向量 + 关键词）
     *
     * @param query 用户查询
     * @param topK  返回数量
     * @return 检索到的文档列表
     */
    List<RetrievedDocument> hybridRetrieve(String query, int topK);
}

