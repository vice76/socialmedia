package com.socialmedia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.socialmedia.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer>{
	
	User findByEmail(String email);
	
	User findById(int id);
}
