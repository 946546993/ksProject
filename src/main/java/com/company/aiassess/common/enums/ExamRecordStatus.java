package com.company.aiassess.common.enums;

import lombok.Getter;

/**
 * 考核记录状态 [SDD §3.1 状态机]
 * <p>
 * RECALCULATED 为终态之一（不回迁 COMPLETED），
 * 报表统计时 COMPLETED 与 RECALCULATED 都计入有效成绩。
 */
@Getter
public enum ExamRecordStatus {

    IN_PROGRESS(1, "进行中"),
    COMPLETED(2, "已完成"),
    EXPIRED_VOID(3, "超时作废"),
    RECALCULATED(4, "已重算");

    private final int code;
    private final String desc;

    ExamRecordStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
