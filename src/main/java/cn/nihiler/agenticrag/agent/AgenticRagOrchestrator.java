package cn.nihiler.agenticrag.agent;

import cn.nihiler.agenticrag.dto.ChatRequest;
import cn.nihiler.agenticrag.dto.ChatResponse;

/**
 * Agentic RAG 编排器接口
 * 
 * 这是整个Agent的核心入口，负责协调各个组件的工作流程：
 * 1. 语义路由 -> 决定走哪条路
 * 2. 检索 -> 获取相关信息
 * 3. 自省 -> 评估检索结果
 * 4. 生成 -> 生成最终回答
 * 
 * TODO: 你需要实现这个接口，把所有组件串联起来
 */
public interface AgenticRagOrchestrator {

    /**
     * 处理用户聊天请求
     *
     * @param request 聊天请求
     * @return 聊天响应
     */
    ChatResponse chat(ChatRequest request);

    /**
     * 流式处理用户聊天请求
     *
     * @param request  聊天请求
     * @param callback 流式回调
     */
    void chatStream(ChatRequest request, StreamCallback callback);

    /**
     * 流式回调接口
     */
    interface StreamCallback {
        void onToken(String token);
        void onComplete();
        void onError(Throwable error);
    }
}

