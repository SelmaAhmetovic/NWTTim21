package io.team21.userservice;

import io.team21.userservice.controller.RoleController;
import io.team21.userservice.entity.Role;
import io.team21.userservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

	@Autowired
	//private static RoleService roleService;

	public static void main(String[] args) {

		SpringApplication.run(UserServiceApplication.class, args);
/*
		if(roleService.countRoles() == 0){
			RoleModel tmp = new RoleModel();
			tmp.setName("admin");
			roleService.addRole(tmp);
			tmp.setName("student");
			roleService.addRole(tmp);
			tmp.setName("professor");
			roleService.addRole(tmp);
			tmp.setName("assistant");
			roleService.addRole(tmp);
		}*/


	}



}
