package com.apigateway.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private String username;
  private Long userId;
  private Collection<? extends GrantedAuthority> authorities;
 
  public JwtResponse(String accessToken, String username, Long userId,Collection<? extends GrantedAuthority> authorities) {
    this.token = accessToken;
    this.username = username;
    this.authorities = authorities;
    this.userId = userId;
  }
 
  public String getAccessToken() {
    return token;
  }
 
  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }
 
  public String getTokenType() {
    return type;
  }
 
  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }
 
  public String getUsername() {
    return username;
  }
 
  public void setUsername(String username) {
    this.username = username;
  }
  
  public Long getUserId() {
	    return this.userId;
	  }
	 
  public void setUserid(Long userId) {
    this.userId = userId;
  }
	  
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }
  
  
}