package com.example.ticket.domain.seat.dto.response;

import com.example.ticket.domain.seat.entity.SeatState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class SeatResponseDto {

    @NotNull
    private final Long seatId;

    @NotBlank
    private final String seatNum;

    @NotNull
    private final SeatState state;

}
