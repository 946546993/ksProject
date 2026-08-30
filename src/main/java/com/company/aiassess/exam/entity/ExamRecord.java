package com.company.aiassess.exam.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 考核记录表 t_exam_record
 * <p>
 * 乐观锁 version 字段用于并发更新（提交答案/重算）。
 */
@Data
@TableName("t_exam_record")
public class ExamRecord {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 记录编号 ER+yyyyMMdd+seq */
    private String recordNo;

    private Long userId;

    private Long sessionId;

    /** 第几次作答 */
    private Integer attempt;

    /** 首次记录ID（重考关联） */
    private Long parentId;

    /** 是否该场次有效成绩（取高分） */
    private Integer isBest;

    /** 1进行中 2已完成 3超时作废 4已重算 */
    private Integer status;

    private LocalDateTime startTime;

    /** 开始时间+限时，结算依据 */
    private LocalDateTime deadline;

    private LocalDateTime endTime;

    private Integer durationSeconds;

    private Integer answeredCount;

    /** 全局能力值[-3,3] */
    private BigDecimal thetaGlobal;

    /** 维度1 基础认知 */
    private BigDecimal thetaBasic;

    /** 维度2 提示词与工具 */
    private BigDecimal thetaPrompt;

    /** 维度3 场景判断 */
    private BigDecimal thetaScenario;

    /** 维度4 工程与前沿 */
    private BigDecimal thetaEngineering;

    /** 维度5 Agent落地 */
    private BigDecimal thetaAgent;

    /** 维度6 业务边界判断 */
    private BigDecimal thetaBoundary;

    /** 综合分0-100 */
    private Integer scoreGlobal;

    /** 六维分明细 JSON：[{key,name,score,count}] */
    private String scoreDimensions;

    /** 等级 L1-L5 */
    private String level;

    /** 学习建议（JSON数组） */
    private String advice;

    /** 是否标定期成绩 1是 0否 */
    private Integer isCalibration;

    /** 冗余主管ID，主管查询免 join */
    private Long managerId;

    @Version
    private Integer version;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
