package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;

@Service
public class InsertarDatos {

	@Autowired
	UserService userService;
	
	
	@PostConstruct
	public void init() {
		User userAdmin = new User("admin@email.com", "admin","admin");
		userAdmin.setPassword("admin");
		userAdmin.setRole("ROLE_ADMIN");
		
		User user1 = new User("user1@email.com", "NAcho","Baru");
		user1.setPassword("11111");
		user1.setRole("ROLE_STANDARD");
		
		userService.addUser(userAdmin);
		userService.addUser(user1);
	}
}
