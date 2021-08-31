package com.sshmygin.customerms.exception.details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data
public class ExceptionDetails {
    private LocalDateTime when;
    private String message;
}
