package com.example.ticket.domain.concert.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ConcertScheduleResponseDto {

    private LocalDateTime schedulePart;
}
