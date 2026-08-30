package com.company.aiassess.llm.core;

/**
 * LLM Provider SPI [SDD §7.1]
 * <p>
 * 设计约束（ADR D6）：SPI 只暴露业务语义方法，不暴露 chat/completion 原语。
 * 供应商差异（API 格式、鉴权、限流）全部封在实现类内部，业务层永远感知不到
 * "今天是哪家模型在出题"。
 */
public interface LlmProvider {

    /** internal / public_cloud */
    String providerId();

    /** 出题 */
    QuestionDraft generateQuestion(QuestionGenContext ctx);

    /** 自审 */
    ReviewResult reviewQuestion(QuestionDraft draft);

    /** 建议润色 */
    String polishAdvice(String template, String wrongDigest);

    /** 健康检查（管理后台展示 + 路由参考） */
    boolean isHealthy();
}
