package com.example.ticket.domain.concert.dto.response;


import com.example.ticket.domain.concert.entity.concertState;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public class ConcertDetailResponseDto {

    private String title;
    private String Detail;
    private Long progressTime;
    private String location;
    private String imgURL;
    private List<ConcertScheduleResponseDto> scheduleDate;
}
