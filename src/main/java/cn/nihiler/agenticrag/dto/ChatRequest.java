package cn.nihiler.agenticrag.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 聊天请求DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {

    /**
     * 会话ID（用于多轮对话）
     */
    private String sessionId;

    /**
     * 用户问题
     */
    @NotBlank(message = "问题不能为空")
    private String query;

    /**
     * 是否启用流式输出
     */
    private Boolean stream;

    /**
     * 是否强制使用某种路由（可选，用于调试）
     */
    private String forceRoute;
}

