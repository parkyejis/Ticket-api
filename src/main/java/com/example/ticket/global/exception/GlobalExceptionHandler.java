package com.example.ticket.global.exception;

import com.example.ticket.global.exception.error.BaseErrorCode;
import com.example.ticket.global.response.BaseResponse;
import jakarta.xml.bind.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseResponse<?>> handleCustomException(CustomException e) {
        BaseErrorCode errorCode = e.getErrorCode();
        log.error("Custom 오류 발생 : {}",e.getErrorCode());
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(BaseResponse.error(errorCode.getCode(), errorCode.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<?>> handleValidationException(
            MethodArgumentNotValidException ex) {
        String errorMessages =
                ex.getBindingResult().getFieldErrors().stream()
                        .map(e -> String.format("[%s] %s", e.getField(), e.getDefaultMessage()))
                        .collect(Collectors.joining(" / "));
        log.warn("Validation 오류 발생: {}", errorMessages);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.error("400", errorMessages));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<BaseResponse<Object>> handleNoResourceFound(NoResourceFoundException ex) {
        log.debug("정적 리소스 없음: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(BaseResponse.error("404", "리소스를 찾을 수 없습니다."));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<?>> handlerException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(BaseResponse.error("500", e.getMessage()));
    }
}


