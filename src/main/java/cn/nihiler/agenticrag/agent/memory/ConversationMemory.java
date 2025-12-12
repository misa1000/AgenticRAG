package cn.nihiler.agenticrag.agent.memory;

import java.util.List;

/**
 * 对话记忆接口
 * 
 * 用于存储和检索多轮对话历史
 * 
 * TODO: 你需要实现这个接口，使用Redis存储对话历史
 */
public interface ConversationMemory {

    /**
     * 添加消息到会话
     *
     * @param sessionId 会话ID
     * @param message   消息
     */
    void addMessage(String sessionId, Message message);

    /**
     * 获取会话历史
     *
     * @param sessionId 会话ID
     * @param limit     最大消息数量
     * @return 消息列表
     */
    List<Message> getHistory(String sessionId, int limit);

    /**
     * 清除会话历史
     *
     * @param sessionId 会话ID
     */
    void clearHistory(String sessionId);

    /**
     * 消息记录
     */
    record Message(
            String role,      // user 或 assistant
            String content,
            Long timestamp
    ) {}
}

