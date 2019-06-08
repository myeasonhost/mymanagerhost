package com.eason.report.pull.core.exception;

public class FeignException extends ServiceException{
    public FeignException() {
        super();
    }

    public FeignException(String message) {
        super(message);
    }

    public FeignException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeignException(Throwable cause) {
        super(cause);
    }

    protected FeignException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
