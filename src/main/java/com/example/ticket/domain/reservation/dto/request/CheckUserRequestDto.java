package com.example.ticket.domain.reservation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CheckUserRequestDto {

    @NotBlank
    private final String password;
}
