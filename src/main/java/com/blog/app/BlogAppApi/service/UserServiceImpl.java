package com.blog.app.BlogAppApi.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.BlogAppApi.Repository.UserRepository;
import com.blog.app.BlogAppApi.entity.User;
import com.blog.app.BlogAppApi.exception.ResourceNotFoundException;
import com.blog.app.BlogAppApi.payloads.UserDto;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		String userId = UUID.randomUUID().toString();
		user.setUserId(userId);
		User createUser = this.userRepo.save(user);	
		return this.userToDto(createUser);
	}

	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
       
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException
				("This Data is not present in the database table..."));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updateUser = this.userRepo.save(user);
		return this.userToDto(updateUser);
	}

	@Override
	public UserDto getSingleUser(String userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("This Data not Present in database Table"));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> usersDto = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return usersDto;
	}

	@Override
	public void deleteUser(String userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("The Data not present in database table.."));
	
		this.userRepo.delete(user);
	}

	public User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
	}
}
