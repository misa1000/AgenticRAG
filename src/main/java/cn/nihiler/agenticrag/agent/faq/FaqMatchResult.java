package cn.nihiler.agenticrag.agent.faq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FAQ匹配结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FaqMatchResult {

    /**
     * 是否命中FAQ
     */
    private boolean matched;

    /**
     * 匹配到的FAQ条目
     */
    private FaqEntry faqEntry;

    /**
     * 相似度得分 (0.0 - 1.0)
     */
    private double similarity;

    /**
     * 创建未命中结果
     */
    public static FaqMatchResult notMatched() {
        return FaqMatchResult.builder()
                .matched(false)
                .similarity(0.0)
                .build();
    }

    /**
     * 创建命中结果
     */
    public static FaqMatchResult matched(FaqEntry entry, double similarity) {
        return FaqMatchResult.builder()
                .matched(true)
                .faqEntry(entry)
                .similarity(similarity)
                .build();
    }
}

