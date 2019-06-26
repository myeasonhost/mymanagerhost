package com.eason.report.pull.platform.sgs.exception;

public class SGSException extends Exception{
    public SGSException() {
        super();
    }

    public SGSException(String message) {
        super(message);
    }

    public SGSException(String message, Throwable cause) {
        super(message, cause);
    }

    public SGSException(Throwable cause) {
        super(cause);
    }

    protected SGSException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
