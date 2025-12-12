package cn.nihiler.agenticrag.service;

import cn.nihiler.agenticrag.dto.ChatRequest;
import cn.nihiler.agenticrag.dto.ChatResponse;

/**
 * 聊天服务接口
 */
public interface ChatService {

    /**
     * 处理聊天请求
     *
     * @param request 聊天请求
     * @return 聊天响应
     */
    ChatResponse chat(ChatRequest request);

    /**
     * 清除会话历史
     *
     * @param sessionId 会话ID
     */
    void clearSession(String sessionId);
}

