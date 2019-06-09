package com.apigateway.dto;

import java.util.Collection;
import java.util.List;

public class UserModel {
    public int id;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private List<String> roleNames;

    public UserModel() {
    }
    
    public UserModel(String firstName,String lastName,String userName, String password) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.password = password;
    	this.userName = userName;
    }

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

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}

