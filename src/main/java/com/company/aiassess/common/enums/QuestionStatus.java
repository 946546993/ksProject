package com.company.aiassess.common.enums;

import lombok.Getter;

/**
 * 题目状态 [SDD §3.2 状态机]，INVALIDATED 为终态。
 */
@Getter
public enum QuestionStatus {

    NORMAL(1, "正常"),
    PENDING_REVIEW(2, "待复核"),
    INVALIDATED(3, "已作废");

    private final int code;
    private final String desc;

    QuestionStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
