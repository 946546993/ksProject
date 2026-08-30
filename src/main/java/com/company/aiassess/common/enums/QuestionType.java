package com.company.aiassess.common.enums;

import lombok.Getter;

/**
 * 题型：单选 / 多选
 */
@Getter
public enum QuestionType {

    SINGLE(1, "单选"),
    MULTI(2, "多选");

    private final int code;
    private final String desc;

    QuestionType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
