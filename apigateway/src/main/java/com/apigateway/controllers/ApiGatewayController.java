package com.apigateway.controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apigateway.bll.CalendarService;
import com.apigateway.dto.ApplicationConstants;
import com.apigateway.dto.ApplicationInfo;
import com.apigateway.dto.Response;
import com.apigateway.dto.Room;
import com.apigateway.dto.RoomReservation;
import com.apigateway.dto.UserModel;
import com.apigateway.helpers.ApplicationHelper;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.ribbon.proxy.annotation.Http.HttpMethod;

import aj.org.objectweb.asm.TypeReference;

@RestController
public class ApiGatewayController {

  @Autowired
  private RestTemplate restTemplate;
  
  @Autowired
  private CalendarService service;
  
  @Autowired
  private EurekaClient eurekaClient;
  
  @Autowired
  private ApplicationHelper helper;
  
  @Async
  @RequestMapping("/rooms")
  public ArrayList<Room> GetRooms() {    
      @SuppressWarnings("unchecked")
      String url = helper.getUrl(eurekaClient, ApplicationConstants.RoomsApplication,"/api/rooms");
	  ArrayList<Room>  rooms = restTemplate.getForObject(url, ArrayList.class);
      return rooms;
  } 
  
@SuppressWarnings({ "rawtypes", "unchecked" })
@RequestMapping("/makeReservation/{userID}/{roomID}")
  public Response MakeReservation(@PathVariable Long userID,@PathVariable Long roomID) {	  
	  
	  Room roomResponse = restTemplate.getForObject(helper.getUrl(eurekaClient, ApplicationConstants.RoomsApplication,"/api/rooms/" + roomID),
			  Room.class);
	
     
      UserModel userResponse =  restTemplate.getForObject(helper.getUrl(eurekaClient, ApplicationConstants.UsersApplication,
			  "/user/" + userID), UserModel.class);
      
      RoomReservation roomReservation = service.CreateReservation(userResponse, roomResponse );
      
      Response response = restTemplate.postForObject(helper.getUrl(eurekaClient, ApplicationConstants.RoomsApplication,
			  "/api/reservations"), roomReservation, Response.class);
      
      return response;
  } 
}
