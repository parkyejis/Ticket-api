package com.example.ticket.domain.reservation.entity;

import com.example.ticket.domain.concert.entity.Concert;
import com.example.ticket.domain.concert.entity.ConcertTime;
import com.example.ticket.domain.seat.entity.Level;
import com.example.ticket.domain.seat.entity.Seat;
import io.micrometer.core.annotation.Counted;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservationId", nullable = false)
    private Long Id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "reservedLevel", nullable = false)
    private Level reservedLevel;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "reservedNum", nullable = false)
    private String reservedNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concertId", nullable = false)
    private Concert concert;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seatId", nullable = false)
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduleId", nullable = false)
    private ConcertTime schedule;
}
