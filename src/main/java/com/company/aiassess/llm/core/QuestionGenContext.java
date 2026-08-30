package com.company.aiassess.llm.core;

import lombok.Builder;

import java.util.List;

/**
 * 题目生成上下文（网关输入契约）
 */
@Builder
public record QuestionGenContext(
        String dimension,
        String knowledgePoint,
        double bTarget,
        int difficultyLevel,
        String jobCategory,
        int questionTypeCode,
        List<String> excludedPoints,
        String promptVersion
) {
}
