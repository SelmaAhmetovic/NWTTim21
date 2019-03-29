package io.team21.userservice.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Column;

public class UserRole {
    private int id;

    private int userId;

    private int roleId;

    protected UserRole() {
    }
}

