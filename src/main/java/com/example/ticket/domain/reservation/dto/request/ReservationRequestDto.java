package com.example.ticket.domain.reservation.dto.request;

import com.example.ticket.domain.seat.entity.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ReservationRequestDto {

    private final String email;
    private final String password;
    private final String name;
    private final Level reservedLevel;
    private final LocalDateTime schedule;
    private final Long count; //인원 수
    private final List<String> seatNum; //선택한 자리
}
