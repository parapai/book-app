package com.app.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.book.dto.ApiResponse;
import com.app.book.exception.BadRequestException;
import com.app.book.exception.DataNotFoundException;
import com.app.book.model.Concert;
import com.app.book.model.Reservation;
import com.app.book.repository.ConcertRepository;
import com.app.book.repository.ReservationRepository;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {
	@Mock
    private ConcertRepository concertRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Concert mockConcert;

    @BeforeEach
    void setUp() {
        mockConcert = new Concert();
        mockConcert.setId("C1");
        mockConcert.setBookingStart(LocalTime.now().minusHours(1));
        mockConcert.setBookingEnd(LocalTime.now().plusHours(1));
    }

    @Test
    void testBookTicket_Success() {
        when(concertRepository.findById("C1")).thenReturn(Optional.of(mockConcert));
        when(concertRepository.decrementTicketCounter("C1")).thenReturn(1);
        
        ApiResponse<?> response = reservationService.bookTicket("C1", "User01");

        assertNotNull(response);
        assertEquals("200", response.getStatusCode());
        assertEquals("Success", response.getStatusDesc());
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void testBookTicket_ConcertNotFound() {
        when(concertRepository.findById("C99")).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> {
            reservationService.bookTicket("C99", "User01");
        });
    }

    @Test
    void testBookTicket_SoldOut() {
        when(concertRepository.findById("C1")).thenReturn(Optional.of(mockConcert));
        when(concertRepository.decrementTicketCounter("C1")).thenReturn(0);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            reservationService.bookTicket("C1", "User01");
        });

        assertEquals("Tickets are sold out!", exception.getMessage());
    }

    @Test
    void testBookTicket_WrongTime() {
        mockConcert.setBookingStart(LocalTime.now().plusHours(1));
        mockConcert.setBookingEnd(LocalTime.now().plusHours(2));
        
        when(concertRepository.findById("C1")).thenReturn(Optional.of(mockConcert));

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            reservationService.bookTicket("C1", "User01");
        });

        assertEquals("Booking is not allowed at this time", exception.getMessage());
    }
}
