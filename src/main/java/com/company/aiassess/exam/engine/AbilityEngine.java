package com.company.aiassess.exam.engine;

import java.util.List;

/**
 * 能力计算引擎 —— 纯领域逻辑 [PRD §6.1 / SDD §4.1]
 * <p>
 * 实现类不得依赖 Spring 事务与 Mapper，输入输出全部是值对象，保证可单测。
 */
public interface AbilityEngine {

    /** 计算初始能力值（岗位 + AI 经验先验） */
    double initialTheta(int jobCategoryCode, int aiExpSelfCode);

    /** 答一题后的能力更新，返回新 θ 与答对概率 */
    AbilityUpdateResult update(double theta, double questionB, double actualScore, int answeredCount);

    /** 当前标准误（提前终止判断用） */
    double standardError(List<AnswerSnapshot> answers);

    /** 六维度能力收缩 [PRD §6.4.3] */
    double shrinkDimension(double thetaDim, int dimCount, double thetaGlobal);

    /** 能力值 → 分数 [PRD §6.4.2] */
    int toScore(double theta);
}
