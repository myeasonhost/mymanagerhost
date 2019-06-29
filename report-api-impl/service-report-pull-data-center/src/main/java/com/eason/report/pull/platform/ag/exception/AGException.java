package com.eason.report.pull.platform.ag.exception;

public class AGException extends Exception{
    public AGException() {
        super();
    }

    public AGException(String message) {
        super(message);
    }

    public AGException(String message, Throwable cause) {
        super(message, cause);
    }

    public AGException(Throwable cause) {
        super(cause);
    }

    protected AGException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
