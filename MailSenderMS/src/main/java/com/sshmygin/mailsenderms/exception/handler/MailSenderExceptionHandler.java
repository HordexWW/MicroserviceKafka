package com.sshmygin.mailsenderms.exception.handler;

import com.sshmygin.mailsenderms.exception.AbstractCustomException;
import com.sshmygin.mailsenderms.exception.EmailRecordNotFoundException;
import com.sshmygin.mailsenderms.exception.MailSenderException;
import com.sshmygin.mailsenderms.exception.details.ExceptionDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class MailSenderExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({MailSenderException.class, EmailRecordNotFoundException.class})
    public ExceptionDetails handle (AbstractCustomException e) {
        return ExceptionDetails
                .builder()
                .when(LocalDateTime.now())
                .message(e.getMessage())
                .build();
    }
}
