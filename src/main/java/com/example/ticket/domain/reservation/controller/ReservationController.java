package com.example.ticket.domain.reservation.controller;

import com.example.ticket.domain.reservation.dto.request.CheckUserRequestDto;
import com.example.ticket.domain.reservation.dto.request.LookforReservationRequestDto;
import com.example.ticket.domain.reservation.dto.request.ReservationRequestDto;
import com.example.ticket.domain.reservation.dto.response.LookforReservationResponseDto;
import com.example.ticket.domain.reservation.service.ReservationService;
import com.example.ticket.global.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseResponse<Void>> createReservation(@PathVariable("concert-id")Long concertId, @Valid @RequestBody ReservationRequestDto dto){
        reservationService.reserved(concertId, dto);
        return ResponseEntity.status(201).body(BaseResponse.success(null));
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponse<List<LookforReservationResponseDto>>> getReservation(@Valid @RequestBody LookforReservationRequestDto dto) {
        //이메일 비밀번호를 통해 확인
        List<LookforReservationResponseDto> responseDtoList = reservationService.getReservation(dto);
        return ResponseEntity.status(200).body(BaseResponse.success(responseDtoList));
    }

    public void changeReservation(){}

    @DeleteMapping("/delete/{user-email}/{reservationNum}")
    public ResponseEntity<BaseResponse<Void>> deleteReservation(@PathVariable(name = "reservationNum") String reservationNum, @PathVariable(name = "user-email") String email, @Valid @RequestBody CheckUserRequestDto dto){
        reservationService.deleteReservation(reservationNum, email, dto);
        return ResponseEntity.status(204).body(BaseResponse.success(null));
    }
}
