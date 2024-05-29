package com.ynjabo77.jabouser.exception;

import com.ynjabo77.jabouser.common.ErrorCode;
import lombok.Getter;

/**
 * 自定义异常类
 */

@Getter
public class BusinessException extends RuntimeException {

    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String msg) {
        super(msg);
        this.code = errorCode.getCode();
    }
}
