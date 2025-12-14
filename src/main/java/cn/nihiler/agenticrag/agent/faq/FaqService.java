package cn.nihiler.agenticrag.agent.faq;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FAQ服务
 * 
 * 使用向量相似度匹配用户问题与预设FAQ
 * 存储方案：SimpleVectorStore（内存 + 文件持久化）
 */
@Slf4j
@Service
public class FaqService {

    private final EmbeddingModel embeddingModel;
    private SimpleVectorStore vectorStore;
    
    /**
     * FAQ索引（id -> FaqEntry）
     */
    private final Map<String, FaqEntry> faqIndex = new ConcurrentHashMap<>();

    /**
     * 相似度阈值，超过此值认为命中FAQ
     */
    @Value("${agentic.rag.faq.similarity-threshold:0.85}")
    private double similarityThreshold;

    /**
     * FAQ向量存储文件路径
     */
    @Value("${agentic.rag.faq.store-path:./data/faq-vectors.json}")
    private String storePath;

    public FaqService(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    @PostConstruct
    public void init() {
        // 创建 SimpleVectorStore
        this.vectorStore = SimpleVectorStore.builder(embeddingModel).build();

        // 尝试从文件加载已有的向量
        File storeFile = new File(storePath);
        if (storeFile.exists()) {
            try {
                vectorStore.load(storeFile);
                log.info("从文件加载FAQ向量库: {}", storePath);
            } catch (Exception e) {
                log.warn("加载FAQ向量库失败，将重新初始化: {}", e.getMessage());
            }
        }

        // 初始化一些示例FAQ（实际应该从数据库或配置文件加载）
        initDefaultFaqs();
    }

    /**
     * 初始化默认FAQ（示例）
     * TODO: 实际项目中应该从数据库或配置文件加载
     */
    private void initDefaultFaqs() {
        if (faqIndex.isEmpty()) {
            addFaq(FaqEntry.builder()
                    .id("faq-001")
                    .question("你们公司的联系方式是什么？如何联系客服？")
                    .answer("您可以通过以下方式联系我们：\n- 客服热线：400-xxx-xxxx\n- 邮箱：support@example.com\n- 工作时间：周一至周五 9:00-18:00")
                    .category("联系方式")
                    .build());

            addFaq(FaqEntry.builder()
                    .id("faq-002")
                    .question("如何重置密码？忘记密码怎么办？")
                    .answer("重置密码步骤：\n1. 点击登录页面的'忘记密码'\n2. 输入注册邮箱\n3. 查收重置邮件并点击链接\n4. 设置新密码")
                    .category("账户")
                    .build());

            addFaq(FaqEntry.builder()
                    .id("faq-003")
                    .question("你们支持哪些支付方式？")
                    .answer("我们支持以下支付方式：\n- 支付宝\n- 微信支付\n- 银行卡（借记卡/信用卡）\n- 企业对公转账")
                    .category("支付")
                    .build());

            log.info("已初始化 {} 条默认FAQ", faqIndex.size());
        }
    }

    /**
     * 添加FAQ条目
     */
    public void addFaq(FaqEntry entry) {
        // 存入索引
        faqIndex.put(entry.getId(), entry);

        // 创建Document并添加到向量库
        // 使用 Document.builder() 或三参数构造函数设置ID
        Document doc = Document.builder()
                .id(entry.getId())
                .text(entry.getQuestion())
                .metadata(Map.of(
                        "id", entry.getId(),
                        "answer", entry.getAnswer(),
                        "category", entry.getCategory() != null ? entry.getCategory() : ""
                ))
                .build();

        vectorStore.add(List.of(doc));
        log.debug("添加FAQ: id={}, question={}", entry.getId(), entry.getQuestion());
    }

    /**
     * 批量添加FAQ
     */
    public void addFaqs(List<FaqEntry> entries) {
        entries.forEach(this::addFaq);
        // 持久化
        save();
    }

    /**
     * 匹配用户问题
     * 
     * @param query 用户输入
     * @return 匹配结果
     */
    public FaqMatchResult match(String query) {
        log.debug("FAQ匹配查询: {}", query);

        // 搜索最相似的FAQ
        List<Document> results = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(query)
                        .topK(1)
                        .similarityThreshold(similarityThreshold)
                        .build()
        );

        if (results.isEmpty()) {
            log.debug("未匹配到FAQ");
            return FaqMatchResult.notMatched();
        }

        Document topResult = results.get(0);
        String faqId = topResult.getId();
        FaqEntry entry = faqIndex.get(faqId);

        if (entry == null) {
            // 从metadata重建（如果从文件加载的情况）
            entry = FaqEntry.builder()
                    .id(faqId)
                    .question(topResult.getText())
                    .answer((String) topResult.getMetadata().get("answer"))
                    .category((String) topResult.getMetadata().get("category"))
                    .build();
            faqIndex.put(faqId, entry);
        }

        // 获取相似度得分（SimpleVectorStore的Document没有直接返回score，这里用阈值估算）
        double similarity = 0.9; // TODO: 获取实际相似度得分
        
        log.info("FAQ命中: id={}, question={}, similarity={}", 
                entry.getId(), entry.getQuestion(), similarity);

        return FaqMatchResult.matched(entry, similarity);
    }

    /**
     * 持久化向量库到文件
     */
    public void save() {
        try {
            File storeFile = new File(storePath);
            storeFile.getParentFile().mkdirs();
            vectorStore.save(storeFile);
            log.info("FAQ向量库已保存: {}", storePath);
        } catch (Exception e) {
            log.error("保存FAQ向量库失败", e);
        }
    }

    /**
     * 删除FAQ
     */
    public void removeFaq(String id) {
        faqIndex.remove(id);
        vectorStore.delete(List.of(id));
        save();
    }

    /**
     * 获取所有FAQ
     */
    public List<FaqEntry> getAllFaqs() {
        return List.copyOf(faqIndex.values());
    }
}

