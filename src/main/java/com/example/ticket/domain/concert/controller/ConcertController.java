package com.example.ticket.domain.concert.controller;

import com.example.ticket.domain.concert.dto.request.ConcertRequestDto;
import com.example.ticket.domain.concert.repository.ConcertRepository;
import com.example.ticket.domain.concert.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public void getConcertList(){
        concertService.getConcertList();
    }

    @GetMapping("/detail/{concert-id}")
    public void getConcertDetail(@PathVariable(value="concert-id") Long concertId){
        concertService.getConcertDetail(concertId);
    }

    //수정하기
    public void updateConcert(){

    }

    //지우기
    public void deleteConcert(){

    }
}
