package cn.nihiler.agenticrag.controller;

import cn.nihiler.agenticrag.agent.faq.FaqEntry;
import cn.nihiler.agenticrag.agent.faq.FaqMatchResult;
import cn.nihiler.agenticrag.agent.faq.FaqService;
import cn.nihiler.agenticrag.common.Result;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FAQ管理API
 */
@RestController
@RequestMapping("/api/faq")
@RequiredArgsConstructor
public class FaqController {

    private final FaqService faqService;

    /**
     * 获取所有FAQ
     */
    @GetMapping
    public Result<List<FaqEntry>> listAll() {
        return Result.success(faqService.getAllFaqs());
    }

    /**
     * 添加FAQ
     */
    @PostMapping
    public Result<String> addFaq(@Valid @RequestBody FaqAddRequest request) {
        FaqEntry entry = FaqEntry.builder()
                .id(request.getId() != null ? request.getId() : "faq-" + System.currentTimeMillis())
                .question(request.getQuestion())
                .answer(request.getAnswer())
                .category(request.getCategory())
                .build();
        
        faqService.addFaq(entry);
        faqService.save();  // 持久化
        
        return Result.success(entry.getId());
    }

    /**
     * 批量添加FAQ
     */
    @PostMapping("/batch")
    public Result<Integer> batchAddFaq(@RequestBody List<FaqAddRequest> requests) {
        List<FaqEntry> entries = requests.stream()
                .map(r -> FaqEntry.builder()
                        .id(r.getId() != null ? r.getId() : "faq-" + System.currentTimeMillis())
                        .question(r.getQuestion())
                        .answer(r.getAnswer())
                        .category(r.getCategory())
                        .build())
                .toList();
        
        faqService.addFaqs(entries);
        return Result.success(entries.size());
    }

    /**
     * 删除FAQ
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteFaq(@PathVariable String id) {
        faqService.removeFaq(id);
        return Result.success();
    }

    /**
     * 测试FAQ匹配
     */
    @PostMapping("/match")
    public Result<FaqMatchResult> testMatch(@RequestBody MatchRequest request) {
        FaqMatchResult result = faqService.match(request.getQuery());
        return Result.success(result);
    }

    @Data
    public static class FaqAddRequest {
        private String id;
        @NotBlank(message = "问题不能为空")
        private String question;
        @NotBlank(message = "答案不能为空")
        private String answer;
        private String category;
    }

    @Data
    public static class MatchRequest {
        @NotBlank(message = "查询内容不能为空")
        private String query;
    }
}

