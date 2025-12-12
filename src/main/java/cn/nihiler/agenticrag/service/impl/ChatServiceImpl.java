package cn.nihiler.agenticrag.service.impl;

import cn.nihiler.agenticrag.dto.ChatRequest;
import cn.nihiler.agenticrag.dto.ChatResponse;
import cn.nihiler.agenticrag.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 聊天服务实现
 * 
 * TODO: 在这里注入并调用 AgenticRagOrchestrator
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    // TODO: 注入你实现的 AgenticRagOrchestrator
    // private final AgenticRagOrchestrator orchestrator;

    @Override
    public ChatResponse chat(ChatRequest request) {
        log.info("收到聊天请求: {}", request.getQuery());

        // 生成会话ID（如果没有的话）
        String sessionId = request.getSessionId();
        if (sessionId == null || sessionId.isEmpty()) {
            sessionId = UUID.randomUUID().toString();
        }

        // TODO: 调用 orchestrator.chat(request) 实现真正的Agentic RAG逻辑
        // 目前返回一个占位响应
        return ChatResponse.builder()
                .sessionId(sessionId)
                .answer("脚手架已搭建完成，请实现 AgenticRagOrchestrator 来处理真正的请求。")
                .processingTime(0L)
                .build();
    }

    @Override
    public void clearSession(String sessionId) {
        log.info("清除会话: {}", sessionId);
        // TODO: 从Redis中清除会话历史
    }
}

