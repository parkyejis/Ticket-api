package com.example.ticket.domain.reservation.controller;

import com.example.ticket.domain.reservation.dto.request.LookforReservationRequestDto;
import com.example.ticket.domain.reservation.dto.request.ReservationRequestDto;
import com.example.ticket.domain.reservation.dto.response.LookforReservationResponseDto;
import com.example.ticket.domain.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    //예약하기
    @PostMapping("/{concert-id}")
    public void createReservation(@PathVariable("concert-id")Long concertId, @RequestBody ReservationRequestDto dto){
        reservationService.reserved(concertId, dto);
    }

    @GetMapping("/detail")
    public List<LookforReservationResponseDto> getReservation(@RequestBody LookforReservationRequestDto dto) {
        //이메일 비밀번호를 통해 확인
        List<LookforReservationResponseDto> responseDtoList = reservationService.getReservation(dto);
        return responseDtoList;
    }

    public void changeReservation(){}

    public void deleteReservation(){}
}
