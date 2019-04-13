package com.apigateway.dto;

import java.util.Collection;

public class UserModel {
	public int id;

    public String firstName;

    public String lastName;

    public String userName;

    public String password;

    public void setId(int id) {
        this.id = id;
  }

  public void setFirstName(String firstName) {
        this.firstName = firstName;
  }

  public void setLastName(String lastName) {
        this.lastName = lastName;
  }

  public void setUserName(String userName) {
        this.userName = userName;
  }

  public void setPassword(String password) {
        this.password = password;
  }

  public int getId() {
       return id;
  }

  public String getFirstName() {
       return firstName;
  }
  public String getLastName() {
       return lastName;
  }

  public String getUserName() {
       return userName;
  }

  public String getPassword() {
       return password;
  }
}

