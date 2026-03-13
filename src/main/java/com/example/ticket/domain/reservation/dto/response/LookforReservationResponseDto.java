package com.example.ticket.domain.reservation.dto.response;

import com.example.ticket.domain.seat.entity.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class LookforReservationResponseDto {

    private final String concertTitle;
    private final String concertArea;
    private final LocalDateTime concertDate;
    private final List<String> seatNum;
    private final Level Grade;
    private final String name;
    private final String email;
    private final Long price;
    private final String reservationNum;

}
