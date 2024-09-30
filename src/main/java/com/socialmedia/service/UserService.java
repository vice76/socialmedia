package com.socialmedia.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.socialmedia.model.User;
import com.socialmedia.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User registerUser(User user) {
		user.setUserId(new Random().nextInt(0, 5000));
		user.setRole("USER");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userRepository.save(user);
		return savedUser;
		
	}

	public Boolean loginUser(String email, String password) {
		User savedUser = userRepository.findByEmail(email);
		if(passwordEncoder.matches(password, savedUser.getPassword())) {
			return true;
		}
		return false;
	}

}
