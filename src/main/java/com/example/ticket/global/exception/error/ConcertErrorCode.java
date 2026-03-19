package com.example.ticket.global.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ConcertErrorCode implements BaseErrorCode{
    CONCERT_NOT_FOUND("CONCERT_4040", "공연 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    SEAT_NOT_FOUND("CONCERT_4040", "좌석 정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus status;
}
