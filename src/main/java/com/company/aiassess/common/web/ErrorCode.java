package com.company.aiassess.common.web;

import lombok.Getter;

/**
 * 统一错误码 [SDD §6.1]
 * <p>
 * 分段：0 成功；4xxxx 客户端/业务错误；5xxxx 服务端错误。
 * message 面向开发者，用户可见文案由前端按 code 映射。
 */
@Getter
public enum ErrorCode {

    SUCCESS(0, "ok"),

    /* ---- 4xxxx 客户端/业务错误 ---- */
    UNAUTHORIZED(40101, "未登录或登录已过期"),
    FORBIDDEN(40301, "无权访问"),
    PROFILE_NOT_FILLED(41001, "未补录个人标签"),
    SESSION_CLOSED(41002, "场次未开放"),
    NO_ATTEMPT_LEFT(41003, "作答次数已用完"),
    EXAM_FINISHED(41004, "考试已结束"),
    EXAM_EXPIRED(41005, "考试已超时"),
    ANSWER_SEQ_MISMATCH(41006, "题目序号不匹配"),
    GEN_FAILED(41007, "题目生成失败"),
    ANSWER_SUBMITTING(41008, "并发提交中"),

    /* ---- 5xxxx 服务端错误 ---- */
    LLM_ALL_CHANNEL_DOWN(50001, "LLM 全通道不可用"),
    INTERNAL_ERROR(50002, "系统内部错误");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
