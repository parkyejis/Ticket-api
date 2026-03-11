package com.example.ticket.domain.concert.service;

import com.example.ticket.domain.concert.dto.request.ConcertRequestDto;
import com.example.ticket.domain.concert.dto.response.ConcertDetailResponseDto;
import com.example.ticket.domain.concert.dto.response.ConcertResponseDto;
import com.example.ticket.domain.concert.entity.Concert;
import com.example.ticket.domain.concert.entity.ConcertTime;
import com.example.ticket.domain.concert.repository.ConcertRepository;
import com.example.ticket.domain.concert.repository.ConcertTimeRepository;
import com.example.ticket.domain.seat.entity.Grade;
import com.example.ticket.domain.seat.entity.Level;
import com.example.ticket.domain.seat.repostiory.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConcertService {

    private final ConcertRepository concertRepository;
    private final ConcertTimeRepository concertTimeRepository;
    private final GradeRepository gradeRepository;

    //존재하는 콘서트 리스트 가져오기
    public List<ConcertResponseDto> getConcertList() {
        //검사해줘야하는 것 end 날짜가 지났는지 확인
        LocalDateTime now = LocalDateTime.now();
        List<Concert> list = concertRepository.findByList(now);
        List<ConcertResponseDto> list_dto = new ArrayList<>();

        for (Concert c : list) {
            list_dto.add(ConcertResponseDto.builder()
                    .title(c.getTitle())
                    .location(c.getLocation())
                    .progressTime(c.getProgressTime())
                    .imgURL(c.getImgURL())
                    .build());
        }

        return list_dto;
    }

    public ConcertDetailResponseDto getConcertDetail(Long concertId) {
        //아이디 값을 가진 concert존재하는 지 확인
        LocalDateTime now = LocalDateTime.now();

        Concert concert = concertRepository.findAllWithSchedule(concertId);

        return ConcertDetailResponseDto.builder()
                .title(concert.getTitle())
                .Detail(concert.getDetail())
                .progressTime(concert.getProgressTime())
                .location(concert.getLocation())
                .imgURL(concert.getImgURL())
                .scheduleDate(concert.getSchedule().stream()
                        .map(ConcertTime::getSchedulePart)
                        .collect(Collectors.toList()))
                .build();
    }

    public void createConcert(ConcertRequestDto dto) {
        //공연 정보를 받아서 공연 엔티티 생성
        Concert concert = Concert.builder()
                .title(dto.getTitle())
                .Detail(dto.getConcertDetail())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .progressTime(dto.getProgressTime())
                .imgURL(dto.getImgURL()).build();

        concertRepository.save(concert);

        //스케줄 list를 이용해서 스케줄 모두 저장
        List<LocalDateTime> list = dto.getScheduleDate();
        List<ConcertTime> concertTimes = new ArrayList<>();
        for(LocalDateTime sch: list){
            concertTimes.add(ConcertTime.builder()
                    .schedulePart(sch)
                    .concert(concert)
                    .build());
        }
        concertTimeRepository.saveAll(concertTimes);

        //등급별 가격 각각 저장
        List<Grade> grades = new ArrayList<>();
        HashMap<Level, Long> hash = dto.getPrice();
        for(Level level : hash.keySet()){


            grades.add(Grade.builder()
                    .level(level)
                    .price(hash.get(level))
                    .concert(concert)
                    .build());
        }
        gradeRepository.saveAll(grades);
    }
}


