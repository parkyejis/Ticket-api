package com.example.ticket.domain.concert.repository;

import com.example.ticket.domain.concert.dto.response.ConcertScheduleResponseDto;
import com.example.ticket.domain.concert.entity.ConcertTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ConcertTimeRespository extends JpaRepository<ConcertTime, Long> {

    @Query("select c.schedulePart from ConcertTime c where c.concert.id = :concertId and c.schedulePart >= :now")
    List<ConcertScheduleResponseDto> findByConcertId(@Param("now") LocalDateTime now, @Param("concertId") Long concertId);


}
