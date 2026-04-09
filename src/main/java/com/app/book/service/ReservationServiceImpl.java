package com.app.book.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.book.dto.ApiResponse;
import com.app.book.dto.Data;
import com.app.book.exception.BadRequestException;
import com.app.book.exception.DataNotFoundException;
import com.app.book.model.Concert;
import com.app.book.model.Reservation;
import com.app.book.repository.ConcertRepository;
import com.app.book.repository.ReservationRepository;

import jakarta.transaction.Transactional;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ConcertRepository concertRepository;
    
    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional 
    public ApiResponse<?> bookTicket(String concertId, String userId) {
    	ApiResponse<?> response = new ApiResponse();
    	try {
	    	Optional<Concert> concertDB = concertRepository.findById(concertId);
	    	if(concertDB.isEmpty()) {
	    		throw new DataNotFoundException("Concert not found");
	    	}
	    	Concert concert = concertDB.get();
	        LocalTime now = LocalTime.now();
	
	        if (now.isBefore(concert.getBookingStart()) || now.isAfter(concert.getBookingEnd())) {
	            throw new BadRequestException("Booking is not allowed at this time");
	        }
	
	        int updatedRows = concertRepository.decrementTicketCounter(concertId);
	        
	        if (updatedRows == 0) {
	            throw new BadRequestException("Tickets are sold out!");
	        }
	
	        Reservation reservation = new Reservation();
	        reservation.setConcertId(concert.getId());
	        reservation.setUserId(userId);
	        reservation.setReservationTime(LocalDateTime.now());
	        reservationRepository.save(reservation);
	        
	        
	        
	        
			Data data = new Data();
			data.setOutput("Ticket booked successfully! Booking ID: " + reservation.getId());
			response.setData(data);
			response.setStatusCode("200");
			response.setStatusDesc("Success");
		
    	} catch (BadRequestException e) {
			throw e;
    	} catch (DataNotFoundException e) {
			throw e;
    	} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
    	return response;
    }
}
