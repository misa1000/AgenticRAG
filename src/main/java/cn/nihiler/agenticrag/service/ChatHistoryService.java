package cn.nihiler.agenticrag.service;

import cn.nihiler.agenticrag.entity.ChatHistory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 聊天历史服务接口
 */
public interface ChatHistoryService extends IService<ChatHistory> {

    /**
     * 获取会话历史
     *
     * @param sessionId 会话ID
     * @param limit     最大数量
     * @return 聊天历史列表
     */
    List<ChatHistory> getSessionHistory(String sessionId, int limit);

    /**
     * 删除会话历史
     *
     * @param sessionId 会话ID
     */
    void deleteBySessionId(String sessionId);
}

