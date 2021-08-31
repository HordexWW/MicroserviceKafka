package com.sshmygin.mailsenderms.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractCustomException extends RuntimeException{
    public AbstractCustomException(String message) {
        super(message);
    }
}
