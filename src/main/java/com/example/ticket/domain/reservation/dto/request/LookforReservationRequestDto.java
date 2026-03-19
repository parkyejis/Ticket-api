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

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @NotBlank (message = "이메일은 필수 입력값입니다.")
    private final String email;

    @NotBlank
    private final String password;
}
