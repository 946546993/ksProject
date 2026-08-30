package com.company.aiassess.common.enums;

import lombok.Getter;

/**
 * AI 经验自评 [PRD §6.1]
 * <p>
 * 用于 initialTheta 的经验修正：-0.4 / -0.1 / +0.3 / +0.6。
 */
@Getter
public enum AiExpSelf {

    ALMOST_NEVER(1, "几乎没用过", -0.4),
    OCCASIONALLY(2, "偶尔使用", -0.1),
    FREQUENTLY(3, "常用", 0.3),
    DEEPLY(4, "深度使用", 0.6);

    private final int code;
    private final String desc;
    private final double thetaBonus;

    AiExpSelf(int code, String desc, double thetaBonus) {
        this.code = code;
        this.desc = desc;
        this.thetaBonus = thetaBonus;
    }
}
