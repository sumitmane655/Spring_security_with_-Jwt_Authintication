package com.ecommerce.shopz.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ecommerce.shopz.entity.User;
import com.ecommerce.shopz.repository.UserRepository;


@Component
public class UserInfoDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Optional<User>user = repository.findByUserName(username);
	return user.map(UserInfoUserDetais::new)
	.orElseThrow(()-> new UsernameNotFoundException("user Not Found"+ username));
	

	}

}
