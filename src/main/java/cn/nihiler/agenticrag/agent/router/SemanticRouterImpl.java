package cn.nihiler.agenticrag.agent.router;

import cn.nihiler.agenticrag.agent.faq.FaqMatchResult;
import cn.nihiler.agenticrag.agent.faq.FaqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 语义路由器实现
 * 
 * 路由优先级：
 * 1. FAQ匹配（相似度检测）
 * 2. LLM分类判断
 */
@Slf4j
@Service
public class SemanticRouterImpl implements SemanticRouter {

    private final ChatClient chatClient;
    private final FaqService faqService;

    /**
     * 是否启用FAQ匹配
     */
    @Value("${agentic.rag.faq.enabled:true}")
    private boolean faqEnabled;

    /**
     * 路由分类Prompt
     */
    private static final String ROUTER_PROMPT = """
            你是一个智能路由决策器，请根据用户的输入决定应该采用什么方式获取信息来回答。
            
            请严格遵循以下分类标准：
            - SEARCH: 用户询问实时新闻、天气、股票、或者显然需要联网查询的即时信息。
            - DATABASE: 用户查询具体的业务数据（如订单状态、库存数量、用户信息、销售报表等）。
            - RAG: 用户询问涉及特定领域知识、公司文档、产品手册或需要上下文理解的复杂问题。
            - DIRECT: 简单的问候、闲聊、数学计算或不需要外部信息的逻辑问题。
            
            请分析用户输入，返回你的决策。
            
            {format}
            """;

    public SemanticRouterImpl(OpenAiChatModel openAiChatModel, FaqService faqService) {
        this.chatClient = ChatClient.create(openAiChatModel);
        this.faqService = faqService;
    }

    @Override
    public RouteType route(String query) {
        return routeWithConfidence(query).getRouteType();
    }

    @Override
    public RouteDecision routeWithConfidence(String query) {
        log.debug("开始路由决策, query: {}", query);
        long startTime = System.currentTimeMillis();

        // 1. 首先检查FAQ匹配
        if (faqEnabled) {
            FaqMatchResult faqResult = faqService.match(query);
            if (faqResult.isMatched()) {
                log.info("FAQ命中, id: {}, similarity: {}", 
                        faqResult.getFaqEntry().getId(), 
                        faqResult.getSimilarity());
                
                return RouteDecision.builder()
                        .routeType(RouteType.FAQ)
                        .confidence(faqResult.getSimilarity())
                        .reason("命中FAQ: " + faqResult.getFaqEntry().getQuestion())
                        .faqAnswer(faqResult.getFaqEntry().getAnswer())  // 附带FAQ答案
                        .build();
            }
        }

        // 2. FAQ未命中，调用LLM进行分类
        try {
            BeanOutputConverter<RouteDecision> converter = new BeanOutputConverter<>(RouteDecision.class);
            
            String userPrompt = "用户输入: " + query;

            RouteDecision decision = chatClient.prompt()
                    .user(u -> u.text(ROUTER_PROMPT + "\n" + userPrompt)
                            .param("format", converter.getFormat()))
                    .call()
                    .entity(RouteDecision.class);

            // 补充默认值
            if (decision.getConfidence() == null) {
                decision.setConfidence(0.8);
            }
            if (decision.getReason() == null) {
                decision.setReason("LLM分类决策");
            }

            long elapsed = System.currentTimeMillis() - startTime;
            log.info("路由决策完成: type={}, confidence={}, reason={}, 耗时={}ms",
                    decision.getRouteType(),
                    decision.getConfidence(),
                    decision.getReason(),
                    elapsed);

            return decision;

        } catch (Exception e) {
            log.error("路由决策失败，回退到DIRECT: {}", e.getMessage(), e);
            return RouteDecision.builder()
                    .routeType(RouteType.DIRECT)
                    .confidence(0.5)
                    .reason("路由决策异常，回退到直接回答")
                    .build();
        }
    }
}
