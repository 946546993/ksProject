package com.company.aiassess.question.select;

import org.springframework.stereotype.Component;

/**
 * 选题策略：维度调度与目标难度计算 [SDD §4.2]
 * <p>
 * 难度窗口 ±0.5，候选不足 5 条放宽到 ±1.0，仍无候选降级锚点题并打 warn 日志
 * 监控"难度死区"（PRD 风险 R8）。
 */
@Component
public class DimensionScheduler {

    // TODO: pickDimension（缺口优先/配额轮转/锚点插槽）、targetB、随机选一
}
