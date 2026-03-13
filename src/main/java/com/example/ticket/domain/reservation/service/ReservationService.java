package com.example.ticket.domain.reservation.service;

import com.example.ticket.domain.concert.entity.Concert;
import com.example.ticket.domain.concert.repository.ConcertRepository;
import com.example.ticket.domain.reservation.dto.request.ReservationRequestDto;
import com.example.ticket.domain.reservation.entity.Reservation;
import com.example.ticket.domain.reservation.repository.ReservationRepository;
import com.example.ticket.domain.seat.entity.Seat;
import com.example.ticket.domain.seat.repostiory.SeatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ConcertRepository concertRepository;
    private final SeatRepository seatRepository;

    //예약하기
    @Transactional
    public void reserved(Long concertId, ReservationRequestDto dto) {
        //공연 정보 가져오기
        Concert concert = concertRepository.findById(concertId).orElse(null);
        //이메일 형식 확인하기
        //비밀번호 암호화 하기
        //공연에 대한 좌석 등급과 가격 확인하기 ->

        //예매번호 만들어주기
        //선택 인원이 2이상인 경우 수에 맞게 예매 정보 저장하기
        if (dto.getCount() > 1) {
            List<Reservation> r = new ArrayList<>();

            for(int i=1;i<dto.getCount(); i++) {
                Seat seat = seatRepository.findById(dto.getSeatId().get(i-1)).orElse(null);
                r.add(Reservation.builder()
                                .email(dto.getEmail())
                                .password(dto.getPassword())
                                .name(dto.getName())
                                .reservedLevel(dto.getReservedLevel())
                                .price(dto.getPrice())
                                .reservedNum("aAAa")
                                .concert(concert)
                                .seat(seat)
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
                .build());

    }
}
