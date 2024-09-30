package com.socialmedia.config;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("io.bootify.mongo.repos")
@EnableMongoAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class MongoConfig {
	 @Bean(name = "auditingDateTimeProvider")
	    public DateTimeProvider dateTimeProvider() {
	        return () -> Optional.of(OffsetDateTime.now());
	    }
}
