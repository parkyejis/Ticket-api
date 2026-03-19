package com.example.ticket.domain.concert.controller;

import com.example.ticket.domain.concert.dto.request.ConcertRequestDto;
import com.example.ticket.domain.concert.dto.response.ConcertDetailResponseDto;
import com.example.ticket.domain.concert.dto.response.ConcertResponseDto;
import com.example.ticket.domain.concert.repository.ConcertRepository;
import com.example.ticket.domain.concert.service.ConcertService;
import com.example.ticket.domain.seat.service.SeatService;
import com.example.ticket.global.response.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/concert")
public class ConcertController {

    private final ConcertService concertService;
    private final SeatService seatService;

    //생성
    @PostMapping("/")
    public ResponseEntity<BaseResponse<Void>> createConcert(@Valid @RequestBody ConcertRequestDto dto){
        concertService.createConcert(dto);
        return ResponseEntity.status(201).body(BaseResponse.success(null));
    }

    //읽기
    @GetMapping("/list")
    public ResponseEntity<BaseResponse<List<ConcertResponseDto>>> getConcertList(){
        List<ConcertResponseDto> responseDto = concertService.getConcertList();
        return ResponseEntity.status(200).body(BaseResponse.success(responseDto));
    }

    @GetMapping("/detail/{concert-id}")
    public ResponseEntity<BaseResponse<ConcertDetailResponseDto>> getConcertDetail(@PathVariable(value="concert-id") Long concertId){
        ConcertDetailResponseDto responseDto = concertService.getConcertDetail(concertId);
        return ResponseEntity.status(200).body(BaseResponse.success(responseDto));
    }

    //수정하기 ->
    public void updateConcert(){

    }

    //지우기
    public void deleteConcert(){

    }
}
