package com.company.aiassess.exam.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 超时结算任务 [SDD §8.2]
 * <p>
 * 每分钟扫描 idx_deadline（status=1 AND deadline < now()）：
 * answered_count > 0 → 按已答结算（不足 15 题按 EXPIRED_VOID）；
 * answered_count == 0 → 整场作废，返还重考次数。
 * CAS 语义（UPDATE ... WHERE status=1）天然幂等。
 */
@Slf4j
@Component
public class TimeoutSettleJob {

    @Scheduled(cron = "0 * * * * ?")
    public void settle() {
        // TODO: 扫描过期记录 → 逐条 CAS 结算
        log.debug("超时结算任务触发（骨架阶段空跑）");
    }
}
