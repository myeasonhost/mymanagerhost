package com.eason.report.pull.platform.lmg.exception;

public class LMGException extends Exception{
    public LMGException() {
        super();
    }

    public LMGException(String message) {
        super(message);
    }

    public LMGException(String message, Throwable cause) {
        super(message, cause);
    }

    public LMGException(Throwable cause) {
        super(cause);
    }

    protected LMGException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
