package com.ecommerce.shopz.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.shopz.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserName(String userName);

}
