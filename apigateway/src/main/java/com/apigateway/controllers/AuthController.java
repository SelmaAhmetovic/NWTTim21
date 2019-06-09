package com.apigateway.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apigateway.dto.ApplicationConstants;
import com.apigateway.dto.JwtResponse;
import com.apigateway.dto.LoginForm;
import com.apigateway.dto.SignUpForm;
import com.apigateway.dto.UserModel;
import com.apigateway.helpers.ApplicationHelper;
import com.apigateway.security.jwt.JwtProvider;
import com.apigateway.security.services.UserPrinciple;
import com.netflix.discovery.EurekaClient;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    private EurekaClient eurekaClient;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private ApplicationHelper helper;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UserPrinciple principle = (UserPrinciple)authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), principle.getId(), userDetails.getAuthorities()));
    }
    
    
    @PostMapping("/signup")
    public String registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        
        // Creating user's account
        UserModel user = new UserModel(signUpRequest.getFirstName(),signUpRequest.getLastName(), signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));

       
        String response = restTemplate.postForObject(helper.getUrl(eurekaClient, ApplicationConstants.UsersApplication,
                "/user"), user, String.class);
        

        return "Registered Successfully";
    }
}