package com.apigateway.bll;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.apigateway.dto.Room;
import com.apigateway.dto.UserModel;
import com.apigateway.dto.RoomReservation;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CalendarService {

	public RoomReservation CreateReservation(UserModel user, Room room) {	
		
		RoomReservation reservation = new RoomReservation();
		reservation.setUserId(user.id);
		reservation.setRoom(room);
		@SuppressWarnings("deprecation")
		Date date = new Date(4,13,2019);
		reservation.setReservationTime(date);
		return reservation;		
	}

	public List<RoomReservation> FilterReservations (List<RoomReservation> reservations, String userId) {
		int newUserId = Integer.parseInt(userId);
		List<RoomReservation> filteredReservations = new ArrayList<RoomReservation>();
		List<RoomReservation> mapped = new ArrayList<RoomReservation>();
		ObjectMapper objectMapper = new ObjectMapper();

		for(int i=0; i< reservations.size(); i++) {
			RoomReservation model = objectMapper.convertValue(reservations.get(i), RoomReservation.class);
			mapped.add(model);
		}
		try {
			for (RoomReservation temp : mapped) {
				int i=0;
				int tempId = temp.getUserId();
				if (tempId == newUserId) {
					filteredReservations.add(temp);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		if(filteredReservations.size() !=0) {
			return filteredReservations;
		} else {
			return null;
		}
	}
}
