package com.calendar.calendarapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author adijata
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
    @GetMapping("/check")
    public String status() {
        return "working";
    }
}