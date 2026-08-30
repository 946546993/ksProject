package com.company.aiassess.common.enums;

import lombok.Getter;

/**
 * 知识维度 [PRD §4.2 / SDD §2.4]
 * <p>
 * bMin/bMax 为该维度的难度区间，quota 为 25 题制下的配额。
 */
@Getter
public enum Dimension {

    BASIC("基础认知", -3.0, 0.0, 4),
    PROMPT("提示词与工具", -2.0, 1.0, 4),
    SCENARIO("场景判断", -1.0, 2.0, 5),
    ENGINEERING("工程与前沿", 0.0, 3.0, 4),
    AGENT("业务场景落地为Agent", 1.0, 3.0, 4),
    BOUNDARY("业务边界判断", 1.0, 3.0, 4);

    private final String name;
    private final double bMin;
    private final double bMax;
    private final int quota;

    Dimension(String name, double bMin, double bMax, int quota) {
        this.name = name;
        this.bMin = bMin;
        this.bMax = bMax;
        this.quota = quota;
    }
}
