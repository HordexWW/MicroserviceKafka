package com.sshmygin.customerms.exception.handler;

import com.sshmygin.customerms.exception.AbstractCustomException;
import com.sshmygin.customerms.exception.details.ExceptionDetails;
import com.sshmygin.customerms.exception.TakenEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomerMicroserviceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TakenEmailException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    private ExceptionDetails handleCustomException(AbstractCustomException e) {
        return ExceptionDetails
                .builder()
                .message(e.getMessage())
                .when(LocalDateTime.now())
                .build();
    }
}
