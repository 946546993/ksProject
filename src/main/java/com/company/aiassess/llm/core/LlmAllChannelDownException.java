package com.company.aiassess.llm.core;

/**
 * LLM 全通道不可用（错误码 50001），上层捕获后降级锚点题兜底。
 */
public class LlmAllChannelDownException extends RuntimeException {

    public LlmAllChannelDownException() {
        super("LLM all channels down");
    }
}
