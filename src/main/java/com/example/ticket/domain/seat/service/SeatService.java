package com.example.ticket.domain.seat.service;

import com.example.ticket.domain.concert.repository.ConcertRepository;
import com.example.ticket.domain.seat.dto.response.GradeResponseDto;
import com.example.ticket.domain.seat.dto.response.SeatResponseDto;
import com.example.ticket.domain.seat.entity.Grade;
import com.example.ticket.domain.seat.entity.Level;
import com.example.ticket.domain.seat.entity.Seat;
import com.example.ticket.domain.seat.repostiory.GradeRepository;
import com.example.ticket.domain.seat.repostiory.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final ConcertRepository concertRepository;
    private final SeatRepository seatRepository;
    private final GradeRepository gradeRepository;

    //레벨 별 가격 가져오기
    public GradeResponseDto getSeatPrice(Long concertId) {
        //공연 존재하는 지 확인
        if(!concertRepository.existsById(concertId)) throw new IllegalAccessException(null);
        //좌석 레벨 가격 알려주기
        List<Grade> grades = gradeRepository.findAllByConsertId(concertId);

        Map<Level, Long> list = new HashMap<>();
        Map<Level, Long> price = new HashMap<>();

        for(Grade g: grades){
            list.put(g.getLevel(), g.getId());
            price.put(g.getLevel(), g.getPrice());
        }

        return GradeResponseDto.builder()
                .level(list)
                .price(price)
                .build();
    }


    //남은 좌석 가져오기
    public void getRemainSeats(Long scheduleId){
        //시간표에 맞는 좌석 list 가져오기
        List<Seat> seats = seatRepository.findAllByScheduleId(scheduleId);
        //좌석의 id, 번호, 상태 전달하기
        List<SeatResponseDto> response = new ArrayList<>();
        for(Seat s :seats){
            response.add(SeatResponseDto.builder()
                    .seatId(s.getId())
                    .seatNum(s.getSeatNum())
                    .state(s.getState()).build());
        }
    }
}
