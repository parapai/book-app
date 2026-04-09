package com.app.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.book.dto.ConcertDto;
import com.app.book.service.ConcertService;
import com.app.book.service.ReservationService;

@RestController
@RequestMapping("api/concerts")
public class ConcertController {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private ConcertService concertService;

	@GetMapping("/search")
	public ResponseEntity<?> searchConcerts(@RequestParam String name) {
		return ResponseEntity.ok(concertService.getConcert(name));
	}

	@PostMapping("/book")
	public ResponseEntity<?> bookTicket(@RequestBody ConcertDto request) {

		return ResponseEntity.ok(reservationService.bookTicket(request.getConcertId(), request.getUserId()));

	}
}
