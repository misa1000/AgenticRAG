package cn.nihiler.agenticrag.agent.tool;

import java.util.List;

/**
 * Web搜索工具接口
 * 
 * 用于搜索即时新闻/实时信息
 * 
 * TODO: 你需要实现这个接口，调用Tavily API或其他搜索API
 */
public interface WebSearchTool {

    /**
     * 执行Web搜索
     *
     * @param query 搜索查询
     * @param maxResults 最大结果数量
     * @return 搜索结果列表
     */
    List<WebSearchResult> search(String query, int maxResults);

    /**
     * 搜索结果
     */
    record WebSearchResult(
            String title,
            String url,
            String snippet,
            Double score
    ) {}
}

