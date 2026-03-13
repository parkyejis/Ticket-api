package com.example.ticket.domain.reservation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LookforReservationRequestDto {

    private final String email;
    private final String password;
}
