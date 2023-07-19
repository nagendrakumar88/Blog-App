package com.blog.app.BlogAppApi.service;

import java.util.List;


import com.blog.app.BlogAppApi.payloads.UserDto;

public interface UserService {
	
	public UserDto createUser(UserDto user);
	public UserDto updateUser(String userId,UserDto user);
	public UserDto getSingleUser(String userId);
	public List<UserDto>getUsers();
	public void deleteUser(String userId);

}
