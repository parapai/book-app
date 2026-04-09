package com.app.book.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.book.model.Concert;

public interface ConcertRepository extends JpaRepository<Concert, String> {

    List<Concert> findByNameContainingIgnoreCaseAndAvailableTicketsGreaterThan(String name, Integer minTickets);

    @Modifying
    @Query("UPDATE Concert c SET c.availableTickets = c.availableTickets - 1 WHERE c.id = :concertId AND c.availableTickets > 0")
    int decrementTicketCounter(@Param("concertId") String concertId);
}
