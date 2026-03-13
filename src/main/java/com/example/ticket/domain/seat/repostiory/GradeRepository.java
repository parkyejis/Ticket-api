package com.example.ticket.domain.seat.repostiory;

import com.example.ticket.domain.seat.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query("select g from Grade g where g.concert.id = :concertId")
    List<Grade> findAllByConsertId(@Param("concertId") Long concertId);
}
