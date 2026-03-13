package com.example.ticket.domain.seat.dto.response;

import com.example.ticket.domain.seat.entity.SeatState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class SeatResponseDto {

    private final Long seatId;
    private final String seatNum;
    private final SeatState state;

}
