package com.company.aiassess.admin.controller;

import com.company.aiassess.common.web.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理后台接口：场次 / 题库 / 复核 / 参数 / 看板 [SDD §6.3 / T4 / T17 / T21]
 */
@Tag(name = "管理后台")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    /** 题目作废：status=3 → 查受影响记录 → 异步批量重算（流水重放） */
    @Operation(summary = "作废题目并触发重算")
    @PostMapping("/question/{id}/invalidate")
    public Result<Void> invalidateQuestion(@PathVariable Long id) {
        // TODO: 作废 → RecalcService 逐记录重放 engine.update → status=RECALCULATED
        throw new UnsupportedOperationException("待实现：SDD §5.3 / T17");
    }

    /** 举报复核：判定无误 / 判定有误（联动作废重算） */
    @Operation(summary = "举报复核")
    @PostMapping("/question/report/{id}/handle")
    public Result<Void> handleReport(@PathVariable Long id) {
        throw new UnsupportedOperationException("待实现：SDD T17");
    }

    // TODO: 场次 CRUD（T4）、参数配置（t_sys_config 热更新）、LLM 看板（T21）
}
