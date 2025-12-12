package cn.nihiler.agenticrag.controller;

import cn.nihiler.agenticrag.common.Result;
import cn.nihiler.agenticrag.dto.ChatRequest;
import cn.nihiler.agenticrag.dto.ChatResponse;
import cn.nihiler.agenticrag.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 聊天API控制器
 */
@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    /**
     * 发送聊天消息
     */
    @PostMapping
    public Result<ChatResponse> chat(@Valid @RequestBody ChatRequest request) {
        ChatResponse response = chatService.chat(request);
        return Result.success(response);
    }

    /**
     * 清除会话历史
     */
    @DeleteMapping("/session/{sessionId}")
    public Result<Void> clearSession(@PathVariable String sessionId) {
        chatService.clearSession(sessionId);
        return Result.success();
    }
}

