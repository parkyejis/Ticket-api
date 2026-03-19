package com.example.ticket.domain.concert.dto.response;


import com.example.ticket.domain.concert.entity.ConcertTime;
import com.example.ticket.domain.concert.entity.concertState;
import com.example.ticket.domain.seat.entity.Level;
import com.example.ticket.domain.seat.entity.Seat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Builder
public class ConcertDetailResponseDto {

    private String title;
    private String Detail;
    private Long progressTime;
    private String location;
    private String imgURL;
    private List<ConcertTime> scheduleDate;
    private Map<Level, Long> price;
    private List<Seat> seats;
}
