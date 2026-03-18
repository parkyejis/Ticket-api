package com.example.ticket.global.exception;

import com.example.ticket.global.exception.error.BaseErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public CustomException(BaseErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
