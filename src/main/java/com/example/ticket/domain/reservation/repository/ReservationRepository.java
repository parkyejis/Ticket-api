package com.example.ticket.domain.reservation.repository;

import com.example.ticket.domain.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("select r from Reservation r where r.email = :email and r.password = :password group by r.reservedNum")
    List<Reservation> findByEmail(@Param("email") String email, @Param("password") String password);

    @Query("select r from Reservation r where r.email = :email and r.reservedNum = :reservationNum")
    List<Reservation> findAllByReservationNum(@Param("reservationNum") String reservationNum, @Param("email") String email);
}
