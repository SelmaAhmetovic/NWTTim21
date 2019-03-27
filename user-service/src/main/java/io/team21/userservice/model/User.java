package io.team21.userservice.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Column;

public class User {
    private int id;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    protected User() {
    }
}

