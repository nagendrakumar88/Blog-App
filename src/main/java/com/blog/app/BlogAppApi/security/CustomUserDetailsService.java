package com.blog.app.BlogAppApi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.blog.app.BlogAppApi.Repository.UserRepository;
import com.blog.app.BlogAppApi.entity.User;
import com.blog.app.BlogAppApi.exception.ResourceNotFoundException;

public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//loding user from database...
		
		User user = this.userRepository.findByEmail(username).orElseThrow(()->
		new ResourceNotFoundException("User Not Present in the database.."));
		
		return user;
	}

}
