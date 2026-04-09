package com.app.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.book.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

}
