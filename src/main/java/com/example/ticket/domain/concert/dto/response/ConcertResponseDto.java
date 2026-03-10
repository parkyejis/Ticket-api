package com.example.ticket.domain.concert.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Builder
public class ConcertResponseDto {
    private String title;
    private String location;
    private Long progressTime;
    private String imgURL;
}
