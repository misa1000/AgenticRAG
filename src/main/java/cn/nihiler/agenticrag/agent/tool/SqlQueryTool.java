package cn.nihiler.agenticrag.agent.tool;

import java.util.List;
import java.util.Map;

/**
 * SQL查询工具接口
 * 
 * 用于查询结构化业务数据
 * 
 * TODO: 你需要实现这个接口
 * 注意：需要做好SQL注入防护，可以使用白名单+参数化查询
 */
public interface SqlQueryTool {

    /**
     * 将自然语言转换为SQL并执行
     *
     * @param naturalLanguageQuery 自然语言查询
     * @return 查询结果
     */
    SqlQueryResult executeNaturalLanguageQuery(String naturalLanguageQuery);

    /**
     * SQL查询结果
     */
    record SqlQueryResult(
            String generatedSql,
            List<Map<String, Object>> data,
            String summary
    ) {}
}

