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
    public void getSeatPrice(@PathVariable("concert-id")Long concertId){
        seatService.getSeatPrice(concertId);
    }
}
