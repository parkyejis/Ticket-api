package com.example.ticket.global.exception.error;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    String getCode();
    String getMessage();
    HttpStatus getStatus();
}
