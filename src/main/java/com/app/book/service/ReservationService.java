package com.app.book.service;

import org.springframework.stereotype.Service;

import com.app.book.dto.ApiResponse;

@Service
public interface ReservationService {

  

    public ApiResponse<?> bookTicket(String concertId, String userId) ;
}
