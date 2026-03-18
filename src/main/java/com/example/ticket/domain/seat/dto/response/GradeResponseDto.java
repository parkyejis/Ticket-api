package com.example.ticket.domain.seat.dto.response;

import com.example.ticket.domain.seat.entity.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@Builder
public class GradeResponseDto {

    private final Map<Level, Long> price;
    private final Map<Level, Long> level;
}
