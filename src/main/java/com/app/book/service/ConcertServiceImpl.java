package com.app.book.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.book.dto.ApiResponse;
import com.app.book.dto.ConcertDto;
import com.app.book.dto.Data;
import com.app.book.model.Concert;
import com.app.book.repository.ConcertRepository;

@Service
public class ConcertServiceImpl implements ConcertService{

    @Autowired
    private ConcertRepository concertRepository;

	@Override
	public ApiResponse<?> getConcert(String name) {
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		List<ConcertDto> dtoList = new ArrayList<>();
		List<Concert> concertList = concertRepository.findByNameContainingIgnoreCaseAndAvailableTicketsGreaterThan(name, 0);
		for(Concert concertEnt: concertList) {
			ConcertDto dto = new ConcertDto();
			dto.setConcertId(concertEnt.getId());
			dto.setName(concertEnt.getName());
			dto.setEventDate(concertEnt.getEventDate().format(dateFormatter));
			dto.setBookingStart(concertEnt.getBookingStart().format(timeFormatter));
			dto.setBookingEnd(concertEnt.getBookingEnd().format(timeFormatter));
			dto.setAvailableTickets(concertEnt.getAvailableTickets());
			dtoList.add(dto);
		}
		ApiResponse<?> response = new ApiResponse();
		Data data = new Data();
		data.setOutputList(dtoList);
		response.setData(data);
		response.setStatusCode("200");
		response.setStatusDesc("Success");
		return response;
	}
    
    
}
