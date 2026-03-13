package com.example.ticket.domain.seat.controller;

import com.example.ticket.domain.seat.service.SeatService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void getRemainSeat(@PathVariable("schedule-id)")Long scheduleId) {
        seatService.getRemainSeats(scheduleId);
    }
}
