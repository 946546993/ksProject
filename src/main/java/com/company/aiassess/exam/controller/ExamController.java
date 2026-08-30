package com.company.aiassess.exam.controller;

import com.company.aiassess.common.web.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 考试接口 [SDD §6.2]
 */
@Tag(name = "考试")
@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
public class ExamController {

    /** 开考：事务内插入 record + 写 θ₀；Redis 锁防并发双开；返回服务端 deadline */
    @Operation(summary = "开考")
    @PostMapping("/start")
    public Result<Long> start(@RequestParam Long sessionId) {
        // TODO: 场次进行中 + attempt 未超 + Redis 锁 lock:start:{userId}:{sessionId}
        throw new UnsupportedOperationException("待实现：SDD §6.2 / 4.2");
    }

    /** 下一题：幂等（nextq:{recordId} 暂存 10min，刷新不跳题）；返回 VO 不含答案/解析 */
    @Operation(summary = "获取下一题")
    @GetMapping("/next")
    public Result<Void> next(@RequestParam Long recordId) {
        // TODO: 选题 → 生成降级 → NextQuestionVO（无答案/解析）
        throw new UnsupportedOperationException("待实现：SDD §5.1");
    }

    /** 提交答案：Redis 锁 → 流水幂等(uk_record_seq) → 判分 → θ 更新(乐观锁) → 计数原子自增 */
    @Operation(summary = "提交答案")
    @PostMapping("/answer")
    public Result<Void> answer(@RequestParam Long recordId,
                               @RequestParam Long questionId,
                               @RequestParam String answer,
                               @RequestParam(required = false) Integer durationSec) {
        throw new UnsupportedOperationException("待实现：SDD §4.4");
    }

    /** 结束考试：幂等；answered_count<15 转 EXPIRED_VOID 不计成绩 */
    @Operation(summary = "结束考试")
    @PostMapping("/finish")
    public Result<Void> finish(@RequestParam Long recordId) {
        throw new UnsupportedOperationException("待实现：SDD §4.6");
    }

    /** 断点续答：恢复进度（剩余时间、已答数、当前题） */
    @Operation(summary = "断点续答")
    @GetMapping("/resume")
    public Result<Void> resume(@RequestParam Long recordId) {
        throw new UnsupportedOperationException("待实现：SDD §10.2-6");
    }
}
