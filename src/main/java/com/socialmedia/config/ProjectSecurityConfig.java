package com.socialmedia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
public class ProjectSecurityConfig {
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		
		//permit all requests inside web application
		http.csrf().ignoringRequestMatchers("/user/post/comment").ignoringRequestMatchers("/api/images/upload")
		.ignoringRequestMatchers("/public/registerUser").and()
		.authorizeHttpRequests()
		.requestMatchers("/public/**").permitAll()
		.requestMatchers("/user/post/viewComment/*").permitAll()
		.requestMatchers("/api/images/all").permitAll()
		.requestMatchers("/api/images/view/**").permitAll()
		.requestMatchers("/user/post/comment").permitAll()
		.requestMatchers("/api/images/upload").permitAll()
		.requestMatchers("/public/registerUser").permitAll()
		.requestMatchers("public/loginUser").permitAll()
		.requestMatchers("/ws/**").permitAll()
		.requestMatchers("/topic/**").permitAll()
		.and().formLogin().loginPage("/login")
		.defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
		.and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
		.and().httpBasic()
		.and().headers().frameOptions().disable()
        .and().csrf().disable()
        .headers()
        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*"))
        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "POST, GET"))
        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Max-Age", "3600"))
        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials", "true"))
        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization"));;
		
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
