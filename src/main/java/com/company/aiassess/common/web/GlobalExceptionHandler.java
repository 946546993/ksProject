package com.company.aiassess.common.web;

import com.company.aiassess.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器：业务异常按 ErrorCode 透出，未知异常统一 50002。
 * traceId 待接入链路追踪后填充（当前先返回 null 占位）。
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** 业务异常：正常业务规则不满足 */
    @ExceptionHandler(BizException.class)
    public Result<Void> handleBiz(BizException e) {
        log.warn("业务异常 code={} msg={}", e.getErrorCode().getCode(), e.getMessage());
        return Result.fail(e.getErrorCode());
    }

    /** 参数校验异常（@Valid / @Validated） */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public Result<Void> handleValidation(BindException e) {
        String detail = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + " " + f.getDefaultMessage())
                .findFirst()
                .orElse("参数校验失败");
        return Result.fail(ErrorCode.INTERNAL_ERROR, detail);
    }

    /** 兜底：未知异常 */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Void> handleUnknown(Exception e) {
        log.error("系统内部错误", e);
        return Result.fail(ErrorCode.INTERNAL_ERROR);
    }
}
