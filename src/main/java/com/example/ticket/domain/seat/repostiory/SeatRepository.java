package com.example.ticket.domain.seat.repostiory;

import com.example.ticket.domain.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Query("select s from Seat s where s.concertTime.scheduleId = :scheduleId")
    List<Seat> findAllByScheduleId(@Param("scheduleId") Long scheduleId);
}
