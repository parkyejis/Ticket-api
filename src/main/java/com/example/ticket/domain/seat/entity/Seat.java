package com.example.ticket.domain.seat.entity;

import com.example.ticket.domain.concert.entity.Concert;
import com.example.ticket.domain.concert.entity.ConcertTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seatId", nullable = false)
    private Long id;

    @Column(name = "seatNum", nullable = false)
    private String seatNum;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private SeatState state = SeatState.Empty;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade", nullable = false)
    private Level level;

    @Column(name = "price", nullable = false)
    private Long price;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "gradeId", nullable = false)
//    private Grade grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduleId", nullable = false)
    private ConcertTime concertTime;

    public void changeSeatState(SeatState state) {
        this.state = state;
    }

}
