package com.company.aiassess.report.controller;

import com.company.aiassess.common.web.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报告接口 [SDD §6.3]
 * <p>
 * 权限校验要点：userId == 当前用户 或 当前用户 == 目标的 manager_id；
 * 下属列表仅返回直接下属（首期不做多层展开）。
 */
@Tag(name = "报告")
@RestController
@RequestMapping("/api/report")
public class ReportController {

    /** 个人画像（六维雷达 + 等级 + 建议） */
    @Operation(summary = "个人画像")
    @GetMapping("/profile/{userId}")
    public Result<Void> profile(@PathVariable Long userId) {
        // TODO: 权限校验（本人或其主管）→ 六维数据 + 等级 + 标定期提示
        throw new UnsupportedOperationException("待实现：SDD T16");
    }

    /** 考核记录明细（报告详情 + 答题明细抽屉） */
    @Operation(summary = "记录详情")
    @GetMapping("/detail/{recordId}")
    public Result<Void> detail(@PathVariable Long recordId) {
        throw new UnsupportedOperationException("待实现：SDD T16");
    }

    /** 我的考核记录列表 */
    @Operation(summary = "我的记录")
    @GetMapping("/history")
    public Result<Void> history() {
        throw new UnsupportedOperationException("待实现：SDD T16");
    }

    /** 主管视图：直接下属列表 */
    @Operation(summary = "下属列表")
    @GetMapping("/subordinates")
    public Result<Void> subordinates() {
        throw new UnsupportedOperationException("待实现：SDD T16");
    }

    /** 导出：流式 Excel（EasyExcel），>5000 行强制异步 + 下载中心 */
    @Operation(summary = "导出报表")
    @GetMapping("/export")
    public Result<Void> export() {
        throw new UnsupportedOperationException("待实现：SDD T16");
    }
}
