package com.sshmygin.mailsenderms.exception;

import org.springframework.http.HttpStatus;

public class MailSenderException extends AbstractCustomException{
    public MailSenderException(String message) {
        super(message);
    }
}
