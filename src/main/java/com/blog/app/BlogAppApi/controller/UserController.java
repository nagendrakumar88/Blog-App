package com.blog.app.BlogAppApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.BlogAppApi.payloads.UserDto;
import com.blog.app.BlogAppApi.service.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/createUser")
	public ResponseEntity<UserDto>createUser(@RequestBody UserDto userDto){
		UserDto createUser = this.userService.createUser(userDto);
		
		return new ResponseEntity<UserDto>(createUser,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<UserDto>updateUser(@RequestParam("userId")String userId
			,@RequestBody UserDto userDto){
		
		UserDto updateUser = this.userService.updateUser(userId, userDto);
		
		return new ResponseEntity<UserDto>(updateUser,HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<UserDto>getUser(@RequestParam("userId")String userId){
		UserDto user = this.userService.getSingleUser(userId);
		
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>>getUSers(){
		List<UserDto> users = this.userService.getUsers();
		return new ResponseEntity<List<UserDto>>(users,HttpStatus.OK);
	}
	
	@GetMapping("/deleteUser")
	public ResponseEntity<UserDto>deleteUser(@RequestParam("userId")String userId){
		this.userService.deleteUser(userId);
		
		return new ResponseEntity<UserDto>(HttpStatus.OK);
	}
}
