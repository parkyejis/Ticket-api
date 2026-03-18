package com.example.ticket.domain.reservation.service;

import com.example.ticket.domain.concert.entity.Concert;
import com.example.ticket.domain.concert.entity.ConcertTime;
import com.example.ticket.domain.concert.repository.ConcertRepository;
import com.example.ticket.domain.concert.repository.ConcertTimeRepository;
import com.example.ticket.domain.reservation.dto.request.CheckUserRequestDto;
import com.example.ticket.domain.reservation.dto.request.LookforReservationRequestDto;
import com.example.ticket.domain.reservation.dto.request.ReservationRequestDto;
import com.example.ticket.domain.reservation.dto.response.LookforReservationResponseDto;
import com.example.ticket.domain.reservation.entity.Reservation;
import com.example.ticket.domain.reservation.repository.ReservationRepository;
import com.example.ticket.domain.seat.entity.Level;
import com.example.ticket.domain.seat.entity.Seat;
import com.example.ticket.domain.seat.entity.SeatState;
import com.example.ticket.domain.seat.repostiory.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ConcertRepository concertRepository;
    private final SeatRepository seatRepository;
    private final ConcertTimeRepository concertTimeRepository;

    //예약하기
    @Transactional
    public void reserved(Long concertId, ReservationRequestDto dto) {
        //공연 정보 가져오기
        Concert concert = concertRepository.findById(concertId).orElse(null);
        //이메일 형식 확인하기
        //비밀번호 암호화 하기
        //공연에 대한 좌석 등급과 가격 확인하기 ->
        //주문번호 동일하게 하기
        //예매번호 만들어주기
        //선택 인원이 2이상인 경우 수에 맞게 예매 정보 저장하기
        ConcertTime concertTime = concertTimeRepository.findById(dto.getScheduleId()).orElse(null);

        if (dto.getCount() > 1) {
            List<Reservation> r = new ArrayList<>();

            for(int i=1;i<dto.getCount(); i++) {
                Seat seat = seatRepository.findById(dto.getSeatId().get(i-1)).orElse(null);
                //해당 좌석 상태 바꿔주기
                seat.changeSeatState(SeatState.Fill);
                r.add(Reservation.builder()
                                .email(dto.getEmail())
                                .password(dto.getPassword())
                                .name(dto.getName())
                                .reservedLevel(dto.getReservedLevel())
                                .price(dto.getPrice())
                                .reservedNum("aAAa")
                                .concert(concert)
                                .seat(seat)
                                .schedule(concertTime)
                        .build());
            }

            reservationRepository.saveAll(r);
            return;
        }

        Seat seat = seatRepository.findById(dto.getSeatId().get(0)).orElse(null);
        reservationRepository.save(Reservation.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .name(dto.getName())
                .reservedLevel(dto.getReservedLevel())
                .price(dto.getPrice())
                .reservedNum("aAAa")
                .concert(concert)
                .seat(seat)
                .schedule(concertTime)
                .build());

    }

    @Transactional(readOnly = true)
    public List<LookforReservationResponseDto> getReservation(LookforReservationRequestDto dto) {
        //이메일이 동일한 모든 예약정보 가져오기
        // 해당 이메일과 비밀번호 확인
        List<Reservation> reservations = reservationRepository.findByEmail(dto.getEmail(), dto.getPassword());
        //이메일 비밀번호 일치하는 정보들 중에 공연 정보 확인

        //공연정보(주문번호) 일치하는 예매정보끼리 묶어서 전달
        Map<String, List<Reservation>> groupedMap = reservations.stream()
                .collect(Collectors.groupingBy(Reservation::getReservedNum));



        //예매 번호 일치하는 경우 좌석, 가격 한번에 출력
        List<LookforReservationResponseDto> responseList = groupedMap.entrySet().stream()
                .map(entry -> {
                    // 예매 번호 뽑기
                    // 해당 예매 번호의 공연 뽑기 -> 공연 제목, 장소 정보 가져오기
                    // 예매 시간id 를 통해 예매 시간 가져오기
                    // 좌석 id 를 통해 좌석을 하나의 List에 넣기
                    // 가격 정보 합 구하기
                    String reservationNum = entry.getKey();
                    List<Reservation> reservation = entry.getValue();
                    Long price = 0l;
                    List<String> seats = new ArrayList<>();
                    for(Reservation r : reservation){
                        price += r.getPrice();
                        seats.add(r.getSeat().getSeatNum());
                    }

                    Concert concert = reservation.getFirst().getConcert();
                    ConcertTime schedule = reservation.getFirst().getSchedule();
                    return new LookforReservationResponseDto(
                            concert.getTitle(),
                            concert.getLocation(),
                            schedule.getSchedulePart(),
                            seats,
                            reservation.getFirst().getReservedLevel(),
                            reservation.getFirst().getName(),
                            reservation.getFirst().getEmail(),
                            price,
                            reservationNum
                    );
                })
                .collect(Collectors.toList());

        return responseList;

    }

    @Transactional
    public void deleteReservation(String reservationNum,String email, CheckUserRequestDto dto) {
        List<Reservation> reservations = reservationRepository.findAllByReservationNum(reservationNum, email);
        // 비번 확인
        List<Long> Ids = new ArrayList<>();
        for(Reservation r : reservations) {
            if(!r.getPassword().equals(dto.getPassword())) {
                break;
            }
            Ids.add(r.getId());
            r.getSeat().changeSeatState(SeatState.Empty);
        }

        reservationRepository.deleteAllById(Ids);

    }
}
