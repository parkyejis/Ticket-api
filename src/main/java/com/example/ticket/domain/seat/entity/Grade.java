package com.example.ticket.domain.seat.entity;

import com.example.ticket.domain.concert.entity.Concert;
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
@Table(name = "grade")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gradeId")
    private Long id;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable=false)
    private Level level = Level.A;

    @Column(name = "price")
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concertId", nullable = false)
    private Concert concert;

}
