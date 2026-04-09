package com.app.book.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ConcertDto {
	
	private String userId;
	private String concertId;
	
	private String name;
	private String eventDate;
	private String bookingStart;
	private String bookingEnd;
	private Long availableTickets;
	

}
