package com.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia.model.Response;
import com.socialmedia.model.User;
import com.socialmedia.service.UserService;

@RestController
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/public/registerUser")
	@CrossOrigin
	public ResponseEntity<User> registerUser(@RequestBody User user){
		
		User registeredUser = userService.registerUser(user);
		 return ResponseEntity
				.status(HttpStatus.CREATED)
				.header("User Registered", "true")
				.body(registeredUser);
		
	}
	
	@GetMapping("/public/loginUser")
	@CrossOrigin
	public ResponseEntity<Response> loginUser(@RequestParam String email , @RequestParam String password ){
		Response res = new Response();
		Boolean loginUser = userService.loginUser(email,password);
		if(loginUser) {
			res.setStatusCode("200");
			res.setStatusMsg("Login SuccesFull");
		}
		else {
			res.setStatusCode("400");
			res.setStatusMsg("Login Failed");
		}
		
		 return ResponseEntity
				.status(HttpStatus.CREATED)
				.header("User Logged In")
				.body(res);
		
	}

}
