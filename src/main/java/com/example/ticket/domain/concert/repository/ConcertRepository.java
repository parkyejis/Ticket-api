package com.example.ticket.domain.concert.repository;

import com.example.ticket.domain.concert.dto.response.ConcertResponseDto;
import com.example.ticket.domain.concert.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ConcertRepository extends JpaRepository<Concert, Long> {

    @Query("select c from Concert c where c.endDate >= :now and c.startDate <= :now order by c.startDate")
    List<ConcertResponseDto> findByList(@Param("now") LocalDateTime now);
}
