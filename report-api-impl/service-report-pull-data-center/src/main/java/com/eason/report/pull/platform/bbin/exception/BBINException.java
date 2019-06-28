package com.eason.report.pull.platform.bbin.exception;

public class BBINException extends Exception{
    public BBINException() {
        super();
    }

    public BBINException(String message) {
        super(message);
    }

    public BBINException(String message, Throwable cause) {
        super(message, cause);
    }

    public BBINException(Throwable cause) {
        super(cause);
    }

    protected BBINException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
