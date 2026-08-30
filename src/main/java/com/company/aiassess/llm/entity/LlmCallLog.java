package com.company.aiassess.llm.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * LLM 调用日志表 t_llm_call_log
 */
@Data
@TableName("t_llm_call_log")
public class LlmCallLog {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 链路ID */
    private String traceId;

    /** 供应商标识 internal / public_cloud */
    private String provider;

    private String model;

    /** GEN / REVIEW / ADVICE */
    private String scene;

    private String promptVersion;

    /** 入参摘要（SHA256 前 255 位，不存全文） */
    private String inputDigest;

    private Integer success;

    private Integer costMs;

    private Integer promptTokens;

    private Integer completionTokens;

    private String errorMsg;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
