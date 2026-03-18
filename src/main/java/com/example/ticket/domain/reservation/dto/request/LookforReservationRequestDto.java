package com.example.ticket.domain.reservation.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LookforReservationRequestDto {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String password;
}
