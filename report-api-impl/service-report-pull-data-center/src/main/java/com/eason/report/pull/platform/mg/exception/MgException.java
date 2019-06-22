package com.eason.report.pull.platform.mg.exception;

public class MgException extends Exception{
    public MgException() {
        super();
    }

    public MgException(String message) {
        super(message);
    }

    public MgException(String message, Throwable cause) {
        super(message, cause);
    }

    public MgException(Throwable cause) {
        super(cause);
    }

    protected MgException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
