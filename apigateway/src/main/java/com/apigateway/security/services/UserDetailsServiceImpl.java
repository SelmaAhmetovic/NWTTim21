package com.apigateway.security.services;


import org.springframework.beans.factory.annotation.Autowired;
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
         UserModel user = restTemplate.getForObject(url, UserModel.class);
         
        return UserPrinciple.build(user);
    }
}