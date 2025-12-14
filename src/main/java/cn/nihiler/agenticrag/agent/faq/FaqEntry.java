package cn.nihiler.agenticrag.agent.faq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FAQ条目
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaqEntry {

    /**
     * FAQ唯一标识
     */
    private String id;

    /**
     * 问题（用于向量化）
     */
    private String question;

    /**
     * 预设答案
     */
    private String answer;

    /**
     * 分类标签（可选）
     */
    private String category;
}

