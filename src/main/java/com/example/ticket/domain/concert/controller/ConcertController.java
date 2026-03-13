package com.example.ticket.domain.concert.controller;

import com.example.ticket.domain.concert.dto.request.ConcertRequestDto;
import com.example.ticket.domain.concert.dto.response.ConcertDetailResponseDto;
import com.example.ticket.domain.concert.dto.response.ConcertResponseDto;
import com.example.ticket.domain.concert.repository.ConcertRepository;
import com.example.ticket.domain.concert.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/concert")
public class ConcertController {

    private final ConcertService concertService;

    //생성
    @PostMapping("/")
    public void createConcert(@RequestBody ConcertRequestDto dto){
        concertService.createConcert(dto);
    }

    //읽기
    @GetMapping("/list")
    public List<ConcertResponseDto> getConcertList(){
        List<ConcertResponseDto> responseDto = concertService.getConcertList();

        return responseDto;
    }

    @GetMapping("/detail/{concert-id}")
    public ConcertDetailResponseDto getConcertDetail(@PathVariable(value="concert-id") Long concertId){
        ConcertDetailResponseDto responseDto = concertService.getConcertDetail(concertId);
        return responseDto;
    }

    //수정하기 ->
    public void updateConcert(){

    }

    //지우기
    public void deleteConcert(){

    }
}
