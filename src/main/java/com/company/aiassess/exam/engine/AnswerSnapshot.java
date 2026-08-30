package com.company.aiassess.exam.engine;

/**
 * 答题快照（标准误计算输入）：作答后能力值 + 题目难度
 */
public record AnswerSnapshot(double thetaAfter, double difficultyB) {
}
