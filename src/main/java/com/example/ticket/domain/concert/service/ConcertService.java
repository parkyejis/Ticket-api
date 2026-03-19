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
import com.example.ticket.domain.seat.entity.Seat;
import com.example.ticket.domain.seat.repostiory.GradeRepository;
import com.example.ticket.domain.seat.repostiory.SeatRepository;
import com.example.ticket.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.ticket.global.exception.error.ConcertErrorCode.CONCERT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ConcertService {

    private final ConcertRepository concertRepository;
    private final ConcertTimeRepository concertTimeRepository;
    private final GradeRepository gradeRepository;
    private final SeatRepository seatRepository;

    //존재하는 콘서트 리스트 가져오기
    @Transactional(readOnly = true)
    public List<ConcertResponseDto> getConcertList() {
        //검사해줘야하는 것 end 날짜가 지났는지 확인
        LocalDateTime now = LocalDateTime.now();
        List<Concert> list = concertRepository.findByList(now);
        if(list == null) { throw new CustomException(CONCERT_NOT_FOUND); }
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

    @Transactional(readOnly = true)
    public ConcertDetailResponseDto getConcertDetail(Long concertId) {
        //아이디 값을 가진 concert존재하는 지 확인
        LocalDateTime now = LocalDateTime.now();

        Concert concert = concertRepository.findAllWithSchedule(concertId);
        if(concert == null) { throw new CustomException(CONCERT_NOT_FOUND); }

        return ConcertDetailResponseDto.builder()
                .title(concert.getTitle())
                .Detail(concert.getDetail())
                .progressTime(concert.getProgressTime())
                .location(concert.getLocation())
                .imgURL(concert.getImgURL())
                .scheduleDate(concert.getSchedule())
                .build();
    }

    @Transactional
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


        //스케줄 리스트만큼 좌석 생성
        List<Seat> seatList = new ArrayList<>();
        //시간 schedule 별로 좌석 만들어야 하고, grade 도 추가해줘야함 (AB, CD, EF, GHIJ)
        for(ConcertTime time : concertTimes){
            Level currentLevel = Level.A;
            Long currentPrice = 0L;
            // 알파벨 루프
            for(char row='A'; row <= 'J'; row++) {

                switch (row){
                    case 'A', 'B' -> {
                        currentLevel = Level.VIP;
                        currentPrice = dto.getPrice().get(currentLevel);
                    }
                    case 'C', 'D' -> {
                        currentLevel = Level.R;
                        currentPrice = dto.getPrice().get(currentLevel);
                    }
                    case 'E', 'F' -> {
                        currentLevel = Level.S;
                        currentPrice = dto.getPrice().get(currentLevel);
                    }
                    default -> { // H, I, J
                        currentLevel = Level.A;
                        currentPrice = dto.getPrice().get(currentLevel);
                    }
                }

                for(int i=1; i<= 10; i++) {
                    String seatNum = row + String.valueOf(i);

                    Seat seat = Seat.builder()
                            .seatNum(seatNum)
                            .level(currentLevel)
                            .price(currentPrice)
                            .concertTime(time)
                            .build();

                    seatList.add(seat);
                }
            }
        }
        seatRepository.saveAll(seatList);

        //등급별 가격 각각 저장
//        List<Grade> grades = new ArrayList<>();
//        Map<Level, Long> hash = dto.getPrice();
//        for(Level level : hash.keySet()){
//
//
//            grades.add(Grade.builder()
//                    .level(level)
//                    .price(hash.get(level))
//                    .concert(concert)
//                    .build());
//        }
//        List<Grade> savedGrade = gradeRepository.saveAll(grades);

        //좌석 정보 추가하는 로직

//        List<Seat> seats = new ArrayList<>();
//        Map<Level, int[]> seat = dto.getSeats();
//        for(Level level: seat.keySet()){
//            for(int i=seat.get(level)[0]; i<seat.get(level)[1]+1; i++){
//                seats.add(Seat.builder()
//                        .seatNum(Seat.changeSeatNum(i))
//                        .grade(grades)
//                        .concertTime(concertTimes)
//                        .build());
//            }
//        }
//        seatRepository.saveAll(seats);
    }
}


