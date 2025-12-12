package cn.nihiler.agenticrag.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 文档实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("document")
public class Document {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文档唯一标识
     */
    private String documentId;

    /**
     * 文档名称
     */
    private String name;

    /**
     * 文档类型 (pdf, docx, txt, etc.)
     */
    private String type;

    /**
     * 文档大小（字节）
     */
    private Long size;

    /**
     * 文档状态 (pending, indexed, failed)
     */
    private String status;

    /**
     * 分块数量
     */
    private Integer chunkCount;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer deleted;
}

