package com.ecommerce.shopz.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.shopz.entity.User;
import com.ecommerce.shopz.repository.AuthReq;
import com.ecommerce.shopz.service.JwtService;
import com.ecommerce.shopz.service.UserService;


@RestController
public class UserController {
	@Autowired
	private UserService service;
	
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/user/new")
	public String createNewUser(@RequestBody User user) {
		
	return service.createNewUser(user);
	
	}
	
	
    @PostMapping("/user/authenticate")
	public String authincateandGetToken(@RequestBody AuthReq authReq) {
    	
   org.springframework.security.core.Authentication authentication 	=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
    
   if(authentication.isAuthenticated()) {
   
      return jwtService.generateToken(authReq.getUsername());
   }else {
	   throw new UsernameNotFoundException("Username is invalid");
   }
		
	}
    
    
	
	

}
