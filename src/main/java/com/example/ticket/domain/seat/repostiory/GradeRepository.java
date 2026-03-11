package com.example.ticket.domain.seat.repostiory;

import com.example.ticket.domain.seat.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
