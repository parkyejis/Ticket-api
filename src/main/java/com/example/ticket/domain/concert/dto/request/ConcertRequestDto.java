package com.example.ticket.domain.concert.dto.request;

import com.example.ticket.domain.seat.entity.Level;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.boot.jaxb.Origin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
public class ConcertRequestDto {

    @NotBlank
    private final String title;

    @NotBlank
    @Size(max = 500, message = "최대 500자만 까지 작성할 수 있습니다.")
    private final String concertDetail;

    @NotNull
    private final LocalDateTime startDate;

    @NotNull
    private final LocalDateTime endDate;

    @NotNull
    private final Long progressTime;

    @NotBlank
    private final String imgURL;

    @NotNull
    private final List<LocalDateTime> scheduleDate;

    @NotNull
    private final Map<Level, Long> price;
}
