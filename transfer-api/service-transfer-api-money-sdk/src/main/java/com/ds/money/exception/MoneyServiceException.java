package com.ds.money.exception;

public class MoneyServiceException extends Exception{
    public MoneyServiceException() {
        super();
    }

    public MoneyServiceException(String message) {
        super(message);
    }

    public MoneyServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MoneyServiceException(Throwable cause) {
        super(cause);
    }

    protected MoneyServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
