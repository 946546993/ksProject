package com.company.aiassess.question.reflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 难度回流任务 [SDD §4.5]
 * <p>
 * 触发：每日 02:00（cron 可配）+ 手动触发接口。
 * 防重：Redis 锁 lock:reflow，锁不住直接跳过（reflow_flag 保证幂等）。
 * 单题失败不阻断批次；串行单线程避免并发写同题。
 * <p>
 * 算法：能力加权校正 → 贝叶斯收缩（w0=10，n 取累计作答数）→ clamp(-3,3)
 * → 乐观锁更新 + 流水打标 + 写 t_difficulty_log（batchNo 整批回滚用）。
 */
@Slf4j
@Component
public class DifficultyReflowJob {

    @Scheduled(cron = "${reflow.cron:0 0 2 * * ?}")
    public void reflow() {
        // TODO: Redis 锁 → selectDirtyQuestionIds(200) → 逐题 reflowOne（异常单题吞掉记日志）
        log.debug("难度回流任务触发（骨架阶段空跑）");
    }

    /** 单题回流：锚点/作废跳过 */
    protected void reflowOne(Long questionId, String batchNo) {
        // TODO: expected/observed → kb 校正 → 贝叶斯收缩 → 乐观锁更新 + 打标 + 日志
        throw new UnsupportedOperationException("待实现：SDD §4.5");
    }
}
