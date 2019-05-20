package com.eason.report.pull.sgs.exception;

public class AgException extends Exception{
    public AgException() {
        super();
    }

    public AgException(String message) {
        super(message);
    }

    public AgException(String message, Throwable cause) {
        super(message, cause);
    }

    public AgException(Throwable cause) {
        super(cause);
    }

    protected AgException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
