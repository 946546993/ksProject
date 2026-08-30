package com.company.aiassess.question.gen;

import com.company.aiassess.llm.core.QuestionDraft;

/**
 * 生成结果结构化校验器 [SDD §4.3.2]
 * <p>
 * LLM JSON Mode 返回后用 networknt json-schema-validator 二次校验（双保险）。
 * 校验规则配置为 classpath:schemas/question-draft.json，代码只加载不硬编码。
 */
public final class SchemaValidator {

    private SchemaValidator() {
    }

    public static void validate(QuestionDraft draft) {
        // TODO: 加载 schemas/question-draft.json → json-schema-validator 校验
        throw new UnsupportedOperationException("待实现：SDD §4.3.2");
    }
}
