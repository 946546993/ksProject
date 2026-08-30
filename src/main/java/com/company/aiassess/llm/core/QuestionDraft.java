package com.company.aiassess.llm.core;

import lombok.Data;

import java.util.List;

/**
 * LLM 生成题目草稿（结构化输出，JSON Mode + Schema 校验通过后的形态）
 */
@Data
public class QuestionDraft {

    private String stem;

    /** [{"key":"A","text":"..."}] */
    private List<Option> options;

    /** ["A"] 或 ["A","C"] */
    private List<String> correctAnswer;

    private String explanation;

    private String knowledgePoint;

    /** 模型自评难度 1~5 档（映射为 b 先验：1→-2.0 ... 5→+2.0） */
    private Integer selfDifficulty;

    @Data
    public static class Option {
        private String key;
        private String text;
    }
}
