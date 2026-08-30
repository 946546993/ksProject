package com.company.aiassess.question.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 题目表 t_question
 * <p>
 * 逻辑删除 + 乐观锁均在此表启用（唯一同时用两者的表）。
 */
@Data
@TableName("t_question")
public class Question {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 题目编号 Q+yyyyMMdd+seq */
    private String questionNo;

    /** 1单选 2多选 */
    private Integer type;

    private String stem;

    /** 选项 JSON：[{"key":"A","text":"..."}] */
    private String options;

    /** 正确答案 JSON：["A"] 或 ["A","C"] */
    private String correctAnswer;

    private String explanation;

    /** 维度枚举 BASIC/PROMPT/SCENARIO/ENGINEERING/AGENT/BOUNDARY */
    private String dimension;

    private String knowledgePoint;

    /** 当前难度值[-3,3] */
    private BigDecimal difficultyB;

    /** 模型自评难度映射的先验值 */
    private BigDecimal difficultyPrior;

    /** 累计作答次数（置信度） */
    private Integer answerCount;

    /** 累计完全正确次数 */
    private Integer correctCount;

    /** TECH/PRODUCT/SALES/FUNC/OTHER/ALL */
    private String jobFit;

    /** 锚点题 1是（难度固定不回流） */
    private Integer isAnchor;

    /** 1模型生成 2人工录入 */
    private Integer source;

    /** 生成模型标识 */
    private String genModel;

    private String genPromptVersion;

    /** 通过即时自审 1是 0否 */
    private Integer selfReviewPassed;

    /** 被标记次数 */
    private Integer reportCount;

    /** 1正常 2待复核 3已作废 */
    private Integer status;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
