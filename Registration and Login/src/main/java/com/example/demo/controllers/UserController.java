package com.example.demo.controllers;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.services.UserService;



@RestController
public class UserController {
	
	private UserService userservice;
	
//		
//	@Value(value = "/demo/src/main/resources/static")
//    private String path;
//	
//	@SuppressWarnings("unused")
//	private static String UPLOADED_FOLDER = "src/main/resources/static";
	
	//login
    @PostMapping("login")
    @ResponseBody
    public Object login(@RequestParam("email") String email ,@RequestParam("password") String password) {		 	
    	System.out.println("out -------------------------------------------------------");
		 return userservice.login(email, password);
	 }
      
    //registration
    @Autowired
    public UserController(UserService userService) 
    { 
    	this.userservice = userService;
    }    
    
    @PostMapping("registration") 
    @ResponseBody
    public Object regisration(@RequestParam("email") String email , @RequestParam("password")String password , 
    		                  @RequestParam("firstName")String firstName  
    		                 ,@RequestParam("lastName")String lastName , @RequestPart("profilePic") MultipartFile file 
    		                 ,@RequestParam("userTypeId") String userTypeId ) throws NumberFormatException, IOException {
    	return userservice.registration(firstName, lastName, email, password, file, Integer.valueOf(userTypeId));
} 
    //=======passsing variable in the heaeder================//
    @RequestMapping(value="getHeaders", method=RequestMethod.POST)
    @ResponseBody 
    public String getHeaders(@RequestHeader("token") String token ){
		return token;
    }        
}