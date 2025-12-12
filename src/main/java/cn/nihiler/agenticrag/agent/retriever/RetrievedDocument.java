package cn.nihiler.agenticrag.agent.retriever;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 检索到的文档
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetrievedDocument {

    /**
     * 文档ID
     */
    private String id;

    /**
     * 文档内容
     */
    private String content;

    /**
     * 相关性得分
     */
    private Double score;

    /**
     * 元数据（来源、标题、创建时间等）
     */
    private Map<String, Object> metadata;
}

