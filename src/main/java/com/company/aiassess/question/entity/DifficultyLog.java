package com.company.aiassess.question.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 难度回流日志表 t_difficulty_log
 */
@Data
@TableName("t_difficulty_log")
public class DifficultyLog {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long questionId;

    private BigDecimal bBefore;

    private BigDecimal bAfter;

    private Integer sampleCount;

    /** 期望正确率 */
    private BigDecimal expectedRate;

    /** 实际正确率 */
    private BigDecimal observedRate;

    private LocalDateTime reflowedAt;

    /** 回流批次号（整批回滚用） */
    private String batchNo;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
