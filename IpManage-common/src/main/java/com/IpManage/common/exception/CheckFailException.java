package com.IpManage.common.exception;


public class CheckFailException extends BaseException {
    public CheckFailException() {
    }

    public CheckFailException(String message) {
        super(message);
    }

    public CheckFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckFailException(Throwable cause) {
        super(cause);
    }

    public CheckFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CheckFailException(String message, String... msgParam) {
        super(message, msgParam);
    }

    public CheckFailException(String message, Object[] params) {
        super(message, params);
    }
}
