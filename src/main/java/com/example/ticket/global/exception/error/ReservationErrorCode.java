package com.example.ticket.global.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReservationErrorCode implements BaseErrorCode{
    PASSWORD_MISMATCH("AUTH001", "비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    RESERVATION_NOT_FOUND("CONCERT_4040", "공연 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR("GLOBAL003", "서버 내부 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    PAGE_SORT_STYLE_WRONG("GLOBAL004", "Page Sort 설정 양식이 잘못되었습니다.", HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus status;
}

/*
* BAD_CREDENTIALS("AUTH001", "아이디 또는 비밀번호가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED),
// 또는
INVALID_PASSWORD("AUTH002", "유효하지 않은 비밀번호입니다.", HttpStatus.BAD_REQUEST),
* VALUE_MISMATCH("GLOBAL002", "입력하신 정보가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
* */