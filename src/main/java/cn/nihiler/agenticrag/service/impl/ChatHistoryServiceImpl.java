package cn.nihiler.agenticrag.service.impl;

import cn.nihiler.agenticrag.entity.ChatHistory;
import cn.nihiler.agenticrag.mapper.ChatHistoryMapper;
import cn.nihiler.agenticrag.service.ChatHistoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 聊天历史服务实现
 */
@Service
public class ChatHistoryServiceImpl extends ServiceImpl<ChatHistoryMapper, ChatHistory>
        implements ChatHistoryService {

    @Override
    public List<ChatHistory> getSessionHistory(String sessionId, int limit) {
        return this.list(new LambdaQueryWrapper<ChatHistory>()
                .eq(ChatHistory::getSessionId, sessionId)
                .orderByDesc(ChatHistory::getCreateTime)
                .last("LIMIT " + limit));
    }

    @Override
    public void deleteBySessionId(String sessionId) {
        this.remove(new LambdaQueryWrapper<ChatHistory>()
                .eq(ChatHistory::getSessionId, sessionId));
    }
}

