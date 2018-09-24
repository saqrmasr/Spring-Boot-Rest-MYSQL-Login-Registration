package com.example.demo.repositories;

/*
 * for the queries 
 * */
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.User;

public interface UserRepository extends JpaRepository<User , Integer>  {
	
	//getUser ID 
	User findUserByUserId(int userId);
	//return userEmail and password
	User findUserByEmailAndPassword(String email, String password);
	//return userInfo by email
	User findUserByEmail(String email);
}
