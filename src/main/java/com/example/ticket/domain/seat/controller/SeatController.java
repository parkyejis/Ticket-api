package com.example.ticket.domain.seat.controller;

import com.example.ticket.domain.seat.dto.response.GradeResponseDto;
import com.example.ticket.domain.seat.dto.response.SeatResponseDto;
import com.example.ticket.domain.seat.service.SeatService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/{concert-id}")
    public GradeResponseDto getSeatPrice(@PathVariable("concert-id")Long concertId){
        GradeResponseDto responseDto = seatService.getSeatPrice(concertId);

        return responseDto;
    }

    @GetMapping("/remain/{schedule-id}")
    public List<SeatResponseDto> getRemainSeat(@PathVariable("schedule-id)")Long scheduleId) {
        List<SeatResponseDto> responseDtos = seatService.getRemainSeats(scheduleId);
        return responseDtos;
    }
}
