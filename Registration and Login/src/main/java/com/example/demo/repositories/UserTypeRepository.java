package com.example.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

//import com.example.demo.models.User;
import com.example.demo.models.UserType;

public interface UserTypeRepository extends JpaRepository <UserType , Integer>{
	
//	UserType findUserByEmail(String typeName);
	UserType findUserTypeById(int id);
} 

	