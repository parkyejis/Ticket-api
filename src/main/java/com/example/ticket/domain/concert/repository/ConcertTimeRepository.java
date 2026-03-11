package com.example.ticket.domain.concert.repository;

import com.example.ticket.domain.concert.entity.ConcertTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertTimeRepository extends JpaRepository<ConcertTime, Long> {
}
