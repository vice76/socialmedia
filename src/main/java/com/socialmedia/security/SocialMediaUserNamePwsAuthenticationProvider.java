package com.socialmedia.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.socialmedia.model.User;
import com.socialmedia.repository.UserRepository;
@Component
public class SocialMediaUserNamePwsAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String email = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		User person = userRepository.findByEmail(email);
		if (null != person && person.getUserId() > 0
				&& passwordEncoder.matches(pwd, person.getPassword()) /* pwd.equals(person.getPwd()) */) {
			return new UsernamePasswordAuthenticationToken(email, null,
					getGrantedAuthorities(person.getRole()));
		} else {
			throw new BadCredentialsException("Invalid Credentials!");
		}
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(String role) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return grantedAuthorities;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}

}
