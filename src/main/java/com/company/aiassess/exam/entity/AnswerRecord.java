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
 * 答题流水表 t_answer_record
 * <p>
 * 只增不改（reflow_flag 除外），物理数据由归档策略管理。
 */
@Data
@TableName("t_answer_record")
public class AnswerRecord {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 考核记录ID */
    private Long recordId;

    /** 冗余 user_id，跨记录统计用 */
    private Long userId;

    private Long questionId;

    /** 作答序号（1 起） */
    private Integer seq;

    /** 用户答案 JSON：["B"] */
    private String userAnswer;

    /** 0 / 0.5 / 1.0 */
    private BigDecimal score;

    /** 是否完全正确 */
    private Integer isCorrect;

    /** 本题用时（秒） */
    private Integer durationSeconds;

    /** 作答时能力值快照 [PRD §7.2] */
    private BigDecimal thetaSnapshot;

    /** 作答时题目难度快照 [PRD §7.2] */
    private BigDecimal difficultySnapshot;

    /** 冗余维度 */
    private String dimension;

    /** 已参与难度回流 0否 1是 */
    private Integer reflowFlag;

    private LocalDateTime answeredAt;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
