package com.app.book.model;


import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "concerts")
public class Concert {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String name;
	
	private LocalDateTime eventDate;
	
	private LocalTime bookingStart;
	private LocalTime bookingEnd;
	
	private Long totalTickets;
	private Long availableTickets;
	

}
