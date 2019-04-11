package com.example.apigateway.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.apigateway.dto.Room;
import com.example.apigateway.dto.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;

@RestController
public class ApiGatewayController {

  @Autowired
  private RestTemplate restTemplate;
  
  @Autowired
  private EurekaClient eurekaClient;

  @Async
  @RequestMapping("/rooms")
  public Collection<Room> GetRooms() {
      Application application = eurekaClient.getApplication("CallendarApp-Rooms");
      InstanceInfo instanceInfo = application.getInstances().get(0);
      String url = "http://"+ instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/api/rooms";
      @SuppressWarnings("unchecked")
	  Collection<Room>  rooms = restTemplate.getForObject(url, Collection.class);
      return rooms;
  } 
  
  @RequestMapping("/makeReservation/{userName}/{roomName}")
  public Collection<Room> MakeReservation(@PathVariable String userName,@PathVariable String roomName) {
      Application roomsService = eurekaClient.getApplication("CallendarApp-Rooms");
      Applications application = eurekaClient.getApplications();
      
      Application usersService = eurekaClient.getApplication("CalendarApp-User");
      
      InstanceInfo instanceInfo = roomsService.getInstances().get(0);
      InstanceInfo usersInstance = usersService.getInstances().get(0);
      
      @SuppressWarnings("unchecked")
	  Collection<Room>  rooms = restTemplate.getForObject("http://"+ instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/api/rooms"
			  , Collection.class);
      
      @SuppressWarnings("unchecked")
	  Collection<User>  users = restTemplate.getForObject("http://"+ usersInstance.getIPAddr() + ":" + usersInstance.getPort() + "/user/allUsers",
			  Collection.class);
      
      
      return rooms;
  } 
}
