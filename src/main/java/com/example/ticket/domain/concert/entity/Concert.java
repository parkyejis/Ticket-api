package com.example.ticket.domain.concert.entity;

import com.example.ticket.domain.seat.entity.Grade;
import com.example.ticket.domain.seat.entity.Seat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "concert", indexes = {
        @Index(name = "idx_end_Date", columnList = "endDate") //endDate를 기준으로 정렬
})
public class Concert {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "concertId", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "Detail", nullable = false)
    private String Detail;

    @Column(name = "startDate", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "endDate", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "progressTime", nullable = false)
    private Long progressTime;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private concertState state = concertState.PROGRESS;

    @Column(name = "location")
    private String location;

    @Column(name = "imgURL")
    private String imgURL;

    @OneToMany(mappedBy = "concert", cascade = CascadeType.ALL)
    private List<ConcertTime> schedule = new ArrayList<>();

}
