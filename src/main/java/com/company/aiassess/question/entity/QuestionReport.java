package com.company.aiassess.question.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 题目举报表 t_question_report
 */
@Data
@TableName("t_question_report")
public class QuestionReport {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long questionId;

    /** 来源考核记录 */
    private Long recordId;

    /** 举报人 */
    private Long userId;

    /** 1答案错误 2选项歧义 3超纲 4表述不清 5其他 */
    private Integer reportType;

    private String description;

    /** 1待处理 2判定无误 3判定有误已作废 */
    private Integer status;

    private Long handlerId;

    private String handleResult;

    private LocalDateTime handledAt;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
