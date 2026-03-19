package com.example.ticket.domain.concert.entity;

import com.example.ticket.domain.seat.entity.Seat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "concertTime")
public class ConcertTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduleId", nullable = false)
    private Long scheduleId;

    @Column(name = "schedulePart", nullable = false)
    private LocalDateTime schedulePart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concertId", nullable = false)
    private Concert concert;

    @OneToMany(mappedBy = "concertTime", cascade = CascadeType.ALL)
    private List<Seat> seats = new ArrayList<>();
}
