package com.apigateway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.apigateway.bll.CalendarService;
import com.apigateway.controllers.ApiGatewayController;
import com.apigateway.dto.Room;
import com.apigateway.dto.RoomReservation;
import com.apigateway.dto.UserModel;

import com.netflix.discovery.EurekaClient;
import com.apigateway.helpers.ApplicationHelper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
@RunWith(MockitoJUnitRunner.class)
public class ApigatewayApplicationTests {

	 @Test
    public void testCreateReservation() {
		 
		CalendarService tester = new CalendarService();
		
		Room room = new Room();
		room.setId(2L);
		room.setLocation("Test location");
		room.setRoomCapacity(35);
		room.setRoomName("Test name");
		
		UserModel user = new UserModel();
		user.setId(1);
		user.setFirstName("Test name");
		user.setLastName("Test last name");
		
		RoomReservation reservation = tester.CreateReservation(user, room);
		
        assertEquals("Room ID is not correct",reservation.getRoom().id, room.id);
        assertEquals("User ID is not correct",reservation.getUserId(), user.id);
        
    }


	    @Mock
	    private RestTemplate restTemplate;
	    
	    @Mock
	    private EurekaClient eurekaClient;
	    
	    @Mock
	    private ApplicationHelper helper;
	 
	    @InjectMocks
	    private ApiGatewayController controller = new ApiGatewayController();
	    
	    @Test
	    public void testControllerCreateReservation() {
			 
			Room room1 = new Room();
			room1.setId(1L);
			room1.setLocation("Test location");
			room1.setRoomCapacity(35);
			room1.setRoomName("Test name 1");
			
			Room room2 = new Room();
			room2.setId(2L);
			room2.setLocation("Test location");
			room2.setRoomCapacity(35);
			room2.setRoomName("Test name 2");
			
			ArrayList<Room> rooms = new ArrayList<Room>();
			rooms.add(room1);
			rooms.add(room2);
			
			Mockito
	          .when(helper.getUrl(eurekaClient, "CallendarApp-Rooms", "/api/rooms"))
	          .thenReturn("http://localhost:8090/api/rooms");
			
			Mockito
	          .when(restTemplate.getForObject("http://localhost:8090/api/rooms", ArrayList.class))
	          .thenReturn(rooms);
			
			ArrayList<Room> response = controller.GetRooms();
			
					
	        assertEquals("Rooms size is not correct",response.size(), 2);
	        assertEquals("first room is not correct",response.get(0).roomName, room1.roomName);
	        assertEquals("second room is not correct",response.get(1).roomName, room2.roomName);
	    }
	 
	 
	 

}
