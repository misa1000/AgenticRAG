package cn.nihiler.agenticrag;

import cn.nihiler.agenticrag.agent.router.RouteType;
import cn.nihiler.agenticrag.agent.router.SemanticRouter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test; // 注意用 Jupiter (JUnit 5)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// 这个注解会启动整个 Spring 容器，就像你运行 main 方法一样
// 这样 ChatClient 才能被自动配置好
@SpringBootTest
class SemanticRouterTest {

    @Autowired
    private SemanticRouter semanticRouter;

    @Test
    void testRealRouting_Search() {
        String query = "今天北京的天气怎么样？";
        RouteType result = semanticRouter.route(query);
        
        System.out.println("Input: " + query + " -> " + result);
        // 断言它应该是 SEARCH
        Assertions.assertEquals(RouteType.SEARCH, result);
    }

    @Test
    void testRealRouting_Database() {
        String query = "帮我查一下订单号 123456 的状态";
        RouteType result = semanticRouter.route(query);

        System.out.println("Input: " + query + " -> " + result);
        Assertions.assertEquals(RouteType.DATABASE, result);
    }
    
    @Test
    void testRealRouting_RAG() {
        // 假设 RAG 是关于企业内部文档的
        String query = "公司的报销流程是怎样的？";
        RouteType result = semanticRouter.route(query);

        System.out.println("Input: " + query + " -> " + result);
        Assertions.assertEquals(RouteType.RAG, result);
    }
}