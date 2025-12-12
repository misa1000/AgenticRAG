package cn.nihiler.agenticrag.agent.router;

/**
 * 语义路由器接口
 * 
 * 核心功能：根据用户输入的问题，决定走哪条处理路径
 * 
 * TODO: 你需要实现这个接口，可以用以下方式之一：
 * 1. 基于LLM的分类（让GPT判断应该走哪条路）
 * 2. 基于Embedding相似度的分类（预定义几个类别的示例，计算相似度）
 * 3. 基于规则的简单分类（关键词匹配等）
 */
public interface SemanticRouter {

    /**
     * 对用户查询进行路由决策
     *
     * @param query 用户输入的问题
     * @return 路由类型
     */
    RouteType route(String query);

    /**
     * 带置信度的路由决策
     *
     * @param query 用户输入的问题
     * @return 路由结果（包含类型和置信度）
     */
    RouteDecision routeWithConfidence(String query);
}

