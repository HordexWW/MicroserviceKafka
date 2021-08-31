package com.sshmygin.customerms.exception;

public abstract class AbstractCustomException extends RuntimeException {

    public AbstractCustomException(String message) {
        super(message);
    }
}
