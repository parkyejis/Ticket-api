package com.example.ticket.domain.seat.repostiory;

import com.example.ticket.domain.seat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
