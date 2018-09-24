package com.example.demo.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.User;
import com.example.demo.models.UserType;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserTypeRepository;
import com.example.demo.util.MultimediaUtil;
import com.example.demo.util.UserValidator;
import com.example.demo.views.ResponseObject;
import com.example.demo.views.UserViews;
//============================== userService contains the business logic of the api ===========================================//

@Service
public class UserService   {
	
	@Autowired
 	public	UserRepository userRepository;
	
	@Autowired
	UserTypeRepository userTypeRepository;
		
    //toDo: retrieve all users 
	public List<User> getAllUsers()
	{   
		return userRepository.findAll();
	}	
	
	//toDo: find the user by its ID 
	public UserViews getUserById(int id) 
	{
		User user = userRepository.findUserByUserId(id);
    //take object from UserViews
		UserViews userViews = new UserViews(user);
		return userViews;
	}
	 
	//User login
	public Object login(String email, String password) {
		/*
		 * ToDo: Implement the validation on the email and password
		 */
		User user = userRepository.findUserByEmailAndPassword(email, password);
		//check if the if the user exists or not
		if (user != null)
			return new UserViews(user);
		else 
			return new ResponseObject(0, "Inavlid email or password!");
	}
	
	//---------------------------------- validation for the registration --------------------------------//	
    // User registration
	public Object registration(String firstName , String lastName , String email , String password, MultipartFile profilePic , int userTypeId) {
		/*
		 * validate the email 
		 */
		if (!UserValidator.validateEmail(email)) {
			 return new ResponseObject(-5, "This is invaild email!"); 
		}
	 /* 
	  *validate the first name 
	  */
		if(!UserValidator.validateFirstname(firstName)) {
			return new ResponseObject(-6, "First Name Is Not Valid!");
		}
		/*
		 * validate last name 
		 */
		if(!UserValidator.validateLastname(lastName)) {
			
			return new ResponseObject(-7, "Last Name Is Not Valid!");
		}
		
		/* 
		 * validate password 
		 */
		
		if(!UserValidator.validatePassword(password))
		{
			return new ResponseObject(-8, "invallid password!");
		}
		
		if (profilePic == null) {
			return new ResponseObject(-4, "Please add your profile picture!");
		}
/*-------------------------------------------------------------------------------------*
 *                                                                                     *
 *-----------------------insert user info into database -------------------------------*/		
		 UserType userType = userTypeRepository.findUserTypeById(userTypeId);
		 User user = userRepository.findUserByEmail(email);
		 
		 //check is the user Type is not exist or exit
		 if (userType == null) {
			return new ResponseObject(-3, "Invalid user type!");
		 }
		 if (user != null) { 
			 return new ResponseObject(-2, "This user is exists!"); 
		 }
		 
		 String imagePath = MultimediaUtil.uploadImages(profilePic);
		 
		 user = new User();
		 user.setEmail(email);
		 user.setFirstName(firstName);
		 user.setLastName(lastName);
		 user.setPassword(password);
		 user.setProfilePic(imagePath);
		 user.setUserType(userType);
		 userRepository.save(user);
		 
		 return new UserViews(user);
	}

}