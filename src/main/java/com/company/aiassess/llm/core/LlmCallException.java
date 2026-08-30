package com.company.aiassess.llm.core;

import lombok.Getter;

/**
 * LLM 单通道调用异常
 */
@Getter
public class LlmCallException extends RuntimeException {

    private final String provider;

    public LlmCallException(String provider, String message, Throwable cause) {
        super(message, cause);
        this.provider = provider;
    }
}
