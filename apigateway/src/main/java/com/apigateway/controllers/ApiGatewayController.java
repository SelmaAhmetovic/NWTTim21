package com.apigateway.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.apigateway.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apigateway.bll.CalendarService;
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
	
     
      WrapperWithModel<UserModel> userResponse =  restTemplate.getForObject(helper.getUrl(eurekaClient, ApplicationConstants.UsersApplication,
			  "/user/" + userID), WrapperWithModel.class);

      ObjectMapper objectMapper = new ObjectMapper();
      UserModel model = objectMapper.convertValue(userResponse.result, UserModel.class);

      RoomReservation roomReservation = service.CreateReservation(model, roomResponse );

      Response response = restTemplate.postForObject(helper.getUrl(eurekaClient, ApplicationConstants.RoomsApplication,
			  "/api/reservations"), roomReservation, Response.class);

      return response;
  }

    @Async
    @RequestMapping("/user")
    public ResponseEntity<WrapperWithModel> GetAllUsers() {
       try{
           @SuppressWarnings("unchecked")
           String url = helper.getUrl(eurekaClient, ApplicationConstants.UsersApplication,"/user");
           WrapperWithModel<List<UserModel>> response = restTemplate.getForObject(url, WrapperWithModel.class);
           return  ResponseEntity.ok().body(response);

       }catch (Exception e){
           WrapperWithModel<String> err = new WrapperWithModel<String>();
           err.message = "Error while getting users...";
           return ResponseEntity.status(500).body(err);
       }
    }


    @RequestMapping("/reservationsByUser/{userID}")
    public ArrayList<RoomReservation> ListAllReservationsByUser(@PathVariable String userID) {
            @SuppressWarnings("unchecked")
            String apiUrl = "/api/reservationsByUser/{userId}";

           String url = helper.getUrl(eurekaClient, ApplicationConstants.RoomsApplication,apiUrl);
            ArrayList<RoomReservation>  response = restTemplate.getForObject(url, ArrayList.class, userID);
            return response;


    }

}
