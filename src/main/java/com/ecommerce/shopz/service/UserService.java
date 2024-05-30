package com.ecommerce.shopz.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ecommerce.shopz.entity.User;
import com.ecommerce.shopz.repository.UserRepository;


@Component
public class UserService {
	
	@Autowired
	UserRepository repository;
	@Autowired
	private PasswordEncoder encoder;
	
	public String createNewUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
	    repository.save(user);
	    return "user has been added";
	   
	}

}
