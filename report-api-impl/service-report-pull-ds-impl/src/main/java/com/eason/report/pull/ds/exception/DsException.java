package com.eason.report.pull.ds.exception;

public class DsException extends Exception{
    public DsException() {
        super();
    }

    public DsException(String message) {
        super(message);
    }

    public DsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DsException(Throwable cause) {
        super(cause);
    }

    protected DsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
