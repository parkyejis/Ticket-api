package com.example.ticket.domain.concert.dto.request;

import com.example.ticket.domain.seat.entity.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.boot.jaxb.Origin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class ConcertRequestDto {
    private final String title;
    private final String concertDetail;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Long progressTime;
    private final String imgURL;

    private final List<LocalDateTime> scheduleDate;
    private final HashMap<Level, Long> price;
}
