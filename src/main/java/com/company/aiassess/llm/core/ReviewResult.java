package com.company.aiassess.llm.core;

import lombok.Data;

import java.util.List;

/**
 * LLM 即时自审结果
 */
@Data
public class ReviewResult {

    private boolean passed;

    private List<String> issues;
}
