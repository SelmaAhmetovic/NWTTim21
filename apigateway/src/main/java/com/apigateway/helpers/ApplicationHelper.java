package com.apigateway.helpers;

import org.springframework.beans.factory.annotation.Autowired;

import com.apigateway.dto.ApplicationInfo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;



public class ApplicationHelper {	

	  public String getUrl(EurekaClient eurekaClient, String applicationName, String route) {
		  Application application = eurekaClient.getApplication(applicationName);
		  InstanceInfo instanceInfo = application.getInstances().get(0);
		  ApplicationInfo appInfo = new ApplicationInfo(instanceInfo.getIPAddr(), Integer.toString(instanceInfo.getPort()));
		  String url = "http://"+ appInfo.IpAddress + ":" + appInfo.Port + route;	  
		  return url;
	  }
}
