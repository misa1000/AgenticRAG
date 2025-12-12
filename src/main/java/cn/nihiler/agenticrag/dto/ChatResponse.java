package cn.nihiler.agenticrag.dto;

import cn.nihiler.agenticrag.agent.router.RouteType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 聊天响应DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {

    /**
     * 会话ID
     */
    private String sessionId;

    /**
     * AI回答
     */
    private String answer;

    /**
     * 使用的路由类型
     */
    private RouteType routeType;

    /**
     * 引用来源（可选）
     */
    private List<SourceReference> sources;

    /**
     * 处理耗时（毫秒）
     */
    private Long processingTime;

    /**
     * 来源引用
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SourceReference {
        private String title;
        private String url;
        private String snippet;
    }
}

