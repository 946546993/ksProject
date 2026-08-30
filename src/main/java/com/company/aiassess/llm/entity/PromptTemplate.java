package com.company.aiassess.llm.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Prompt 模板表 t_prompt_template（热更新优先于资源文件）
 */
@Data
@TableName("t_prompt_template")
public class PromptTemplate {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** GEN / REVIEW / ADVICE */
    private String scene;

    /** v1 / v2 */
    private String version;

    private String systemPrompt;

    /** 含 {placeholder} 占位符 */
    private String userTemplate;

    /** 0草稿 1生效 2下线 */
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
