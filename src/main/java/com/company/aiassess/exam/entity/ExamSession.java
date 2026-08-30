package com.company.aiassess.exam.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 考试场次表 t_exam_session
 */
@Data
@TableName("t_exam_session")
public class ExamSession {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    /** 窗口期开始 */
    private LocalDateTime startTime;

    /** 窗口期结束 */
    private LocalDateTime endTime;

    /** 题量，默认 25 */
    private Integer questionCount;

    /** 限时（分钟），默认 30 */
    private Integer durationMinutes;

    /** 最大作答次数，默认 2 */
    private Integer maxAttempts;

    /** 每维度最低题量，默认 3 */
    private Integer minPerDimension;

    /** 提前终止标准误阈值，默认 0.30 */
    private BigDecimal earlyStopSe;

    /** 0未开始 1进行中 2已结束 */
    private Integer status;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
