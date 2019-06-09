package com.apigateway.security.services;


import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.apigateway.dto.ApplicationConstants;
import com.apigateway.dto.UserModel;
import com.apigateway.helpers.ApplicationHelper;
import com.netflix.discovery.EurekaClient;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ApplicationHelper helper;
	
	@Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EurekaClient eurekaClient;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
    	 @SuppressWarnings("unchecked")
    	 
         String url = helper.getUrl(eurekaClient, ApplicationConstants.UsersApplication, "/user/login/" + username);
         ResponseEntity<UserModel> user = restTemplate.exchange(url,HttpMethod.GET, new HttpEntity<UserModel>(createHeaders("admin", "pwd")),UserModel.class);
         
        return UserPrinciple.build(user.getBody());
    }
    
    HttpHeaders createHeaders(String username, String password){
    	   return new HttpHeaders() {{
    	         String auth = username + ":" + password;
    	         byte[] encodedAuth = Base64.encodeBase64( 
    	            auth.getBytes(Charset.forName("US-ASCII")) );
    	         String authHeader = "Basic " + new String( encodedAuth );
    	         set( "Authorization", authHeader );
    	      }};
    	}
}