package com.apigateway.dto;

public class ApplicationInfo {
	public ApplicationInfo(String ipAddress, String port) {
		IpAddress = ipAddress;
		Port = port;				
	}
	public String IpAddress;
	public String Port;
}
