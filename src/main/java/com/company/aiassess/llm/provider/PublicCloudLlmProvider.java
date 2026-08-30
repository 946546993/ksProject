package com.company.aiassess.llm.provider;

import com.company.aiassess.llm.core.LlmProvider;
import com.company.aiassess.llm.core.QuestionDraft;
import com.company.aiassess.llm.core.QuestionGenContext;
import com.company.aiassess.llm.core.ReviewResult;
import org.springframework.stereotype.Component;

/**
 * 公有云 LLM Provider（降级通道）
 */
@Component
public class PublicCloudLlmProvider implements LlmProvider {

    @Override
    public String providerId() {
        return "public_cloud";
    }

    @Override
    public QuestionDraft generateQuestion(QuestionGenContext ctx) {
        // TODO: 调公有云 OpenAI 兼容接口（base-url/api-key/model 走环境变量）
        throw new UnsupportedOperationException("待实现");
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
