package com.example.ticket.domain.reservation.dto.request;

import com.example.ticket.domain.seat.entity.Level;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ReservationRequestDto {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String password;

    @NotBlank
    private final String name;

    @NotNull
    private final Level reservedLevel;

    @NotNull
    private final Long price;

    @NotNull
    private final LocalDateTime schedule;

    @NotNull
    private final Long count; //인원 수

    @NotNull
    private final List<Long> seatId; //선택한 자리

    @NotNull
    private final Long scheduleId;
}
