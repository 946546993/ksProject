package com.company.aiassess.common.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

/**
 * 统一响应体 [SDD §6.1]
 * <pre>{@code
 * { "code": 0, "message": "ok", "data": { }, "traceId": "..." }
 * }</pre>
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    private final int code;
    private final String message;
    private final T data;
    private final String traceId;

    private Result(int code, String message, T data, String traceId) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.traceId = traceId;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), data, null);
    }

    public static Result<Void> ok() {
        return ok(null);
    }

    public static <T> Result<T> fail(ErrorCode errorCode) {
        return new Result<>(errorCode.getCode(), errorCode.getMessage(), null, null);
    }

    public static <T> Result<T> fail(ErrorCode errorCode, String detail) {
        return new Result<>(errorCode.getCode(), detail, null, null);
    }

    public boolean isSuccess() {
        return code == ErrorCode.SUCCESS.getCode();
    }
}
