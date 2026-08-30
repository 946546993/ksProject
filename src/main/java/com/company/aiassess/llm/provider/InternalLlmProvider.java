package com.company.aiassess.llm.provider;

import com.company.aiassess.llm.core.LlmProvider;
import com.company.aiassess.llm.core.QuestionDraft;
import com.company.aiassess.llm.core.QuestionGenContext;
import com.company.aiassess.llm.core.ReviewResult;
import org.springframework.stereotype.Component;

/**
 * 内部 AI 中台 Provider（primary）
 * <p>
 * 接口规范待 IT 提供（SDD §16 O5），SPI 已抽象，不阻塞并行开发。
 */
@Component
public class InternalLlmProvider implements LlmProvider {

    @Override
    public String providerId() {
        return "internal";
    }

    @Override
    public QuestionDraft generateQuestion(QuestionGenContext ctx) {
        // TODO: 调内部中台接口，JSON Mode 结构化出题
        throw new UnsupportedOperationException("待实现：内部中台接口规范（O5）");
    }

    @Override
    public ReviewResult reviewQuestion(QuestionDraft draft) {
        throw new UnsupportedOperationException("待实现");
    }

    @Override
    public String polishAdvice(String template, String wrongDigest) {
        throw new UnsupportedOperationException("待实现");
    }

    @Override
    public boolean isHealthy() {
        return false;
    }
}
