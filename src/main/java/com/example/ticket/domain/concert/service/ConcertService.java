package com.example.ticket.domain.concert.service;

import com.example.ticket.domain.concert.dto.response.ConcertResponseDto;
import com.example.ticket.domain.concert.repository.ConcertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertService {

    private final ConcertRepository concertRepository;
    private final ConcertTimeRespository concertTimeRespository;

    //존재하는 콘서트 리스트 가져오기
    public void getConcertList() {
        //검사해줘야하는 것 end 날짜가 지났는지 확인
        LocalDateTime now = LocalDateTime.now();
        List<ConcertResponseDto> list = concertRepository.findByList(now);
    }

    public ConcertDetailResponseDto getConcertDetail(Long concertId) {
        //아이디 값을 가진 concert존재하는 지 확인
        LocalDateTime now = LocalDateTime.now();

        Concert concert = concertRepository.findById(concertId).orElse(null);
        List<ConcertScheduleResponseDto> time = concertTimeRespository.findByConcertId(now, concertId);;

        return ConcertDetailResponseDto.builder()
                .title(concert.getTitle())
                .Detail(concert.getDetail())
                .progressTime(concert.getProgressTime())
                .location(concert.getLocation())
                .imgURL(concert.getImgURL())
                .scheduleDate(time)
                .build();
    }



