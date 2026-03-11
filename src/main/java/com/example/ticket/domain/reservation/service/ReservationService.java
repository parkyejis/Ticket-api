package com.example.ticket.domain.reservation.service;

import com.example.ticket.domain.reservation.dto.request.ReservationRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    //예약하기
    @Transactional
    public void reserved(Long concertId, ReservationRequestDto dto) {
        //공연 정보 가져오기
        //이메일 형식 확인하기
        //비밀번호 암호화 하기
        //공연에 대한 좌석 등급과 가격 확인하기 ->
        //선택 인원이 2이상인 경우 수에 맞게 예매 정보 저장하기
    }
}
