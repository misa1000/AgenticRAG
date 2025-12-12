package cn.nihiler.agenticrag.agent.router;

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
     * 决策理由（可选，用于调试）
     */
    private String reason;
}

