package com.apigateway.bll;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Collection;

import com.apigateway.dto.Room;
import com.apigateway.dto.UserModel;
import com.apigateway.dto.RoomReservation;

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
}
