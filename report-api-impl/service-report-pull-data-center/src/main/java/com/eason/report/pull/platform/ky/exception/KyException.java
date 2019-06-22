package com.eason.report.pull.platform.ky.exception;

public class KyException extends Exception{
    public KyException() {
        super();
    }

    public KyException(String message) {
        super(message);
    }

    public KyException(String message, Throwable cause) {
        super(message, cause);
    }

    public KyException(Throwable cause) {
        super(cause);
    }

    protected KyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
