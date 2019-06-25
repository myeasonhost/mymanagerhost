package com.eason.report.pull.platform.pt.exception;

public class PTException extends Exception{
    public PTException() {
        super();
    }

    public PTException(String message) {
        super(message);
    }

    public PTException(String message, Throwable cause) {
        super(message, cause);
    }

    public PTException(Throwable cause) {
        super(cause);
    }

    protected PTException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
