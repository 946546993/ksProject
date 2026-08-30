package com.company.aiassess.question.controller;

import com.company.aiassess.common.web.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 答题端题目相关接口：标记/举报题目 [SDD §10.2-4]
 */
@Tag(name = "题目")
@RestController
@RequestMapping("/api/question")
public class QuestionController {

    /** 标记此题：轻量表单（类型 + 可选描述），不打断答题 */
    @Operation(summary = "举报题目")
    @PostMapping("/report")
    public Result<Void> report(@RequestBody Object reportRequest) {
        // TODO: 写 t_question_report；report_count 自增；>=3 自动转待复核
        throw new UnsupportedOperationException("待实现");
    }
}
