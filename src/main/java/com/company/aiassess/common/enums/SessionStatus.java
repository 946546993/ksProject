package com.company.aiassess.common.enums;

import lombok.Getter;

/**
 * 场次状态
 */
@Getter
public enum SessionStatus {

    NOT_STARTED(0, "未开始"),
    RUNNING(1, "进行中"),
    CLOSED(2, "已结束");

    private final int code;
    private final String desc;

    SessionStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
