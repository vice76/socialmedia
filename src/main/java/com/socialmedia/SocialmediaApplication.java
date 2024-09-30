package com.socialmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.socialmedia.repository.UserRepository;

@SpringBootApplication(/*
						 * scanBasePackages={ "com.socialmedia.model", "com.socialmedia.service",
						 * "com.socialmedia.controller", "com.socialmedia.repository",
						 * "com.socialmedia.security"}
						 */)
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@EntityScan("com.socialmedia.model")
public class SocialmediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialmediaApplication.class, args);
	}

}
