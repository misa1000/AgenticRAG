package cn.nihiler.agenticrag.agent.reflection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自省评估结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReflectionResult {

    /**
     * 检索结果是否足够回答问题
     */
    private Boolean sufficient;

    /**
     * 置信度 (0.0 - 1.0)
     */
    private Double confidence;

    /**
     * 评估理由
     */
    private String reason;

    /**
     * 建议的重写查询（如果不足够的话）
     */
    private String suggestedRewrite;
}

