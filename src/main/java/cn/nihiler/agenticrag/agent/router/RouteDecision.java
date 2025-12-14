package cn.nihiler.agenticrag.agent.router;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 路由决策结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDecision {

    /**
     * 路由类型
     */
    private RouteType routeType;

    /**
     * 置信度 (0.0 - 1.0)
     */
    private Double confidence;

    /**
     * 决策理由（用于调试和日志）
     */
    private String reason;

    /**
     * FAQ答案（仅当routeType=FAQ时有值）
     * JsonIgnore: 这个字段不参与LLM的JSON解析
     */
    @JsonIgnore
    private String faqAnswer;
}
