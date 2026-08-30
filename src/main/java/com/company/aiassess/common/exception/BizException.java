package com.company.aiassess.common.exception;

import com.company.aiassess.common.web.ErrorCode;
import lombok.Getter;

/**
 * 业务异常：业务规则不满足时抛出，由全局异常处理器兜底转换为统一响应体。
 */
@Getter
public class BizException extends RuntimeException {

    private final ErrorCode errorCode;

    public BizException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BizException(ErrorCode errorCode, String detail) {
        super(errorCode.getMessage() + ": " + detail);
        this.errorCode = errorCode;
    }
}
