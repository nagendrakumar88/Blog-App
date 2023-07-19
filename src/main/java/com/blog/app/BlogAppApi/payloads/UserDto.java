package com.blog.app.BlogAppApi.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private String userId;
	private String name;
	
	private String email;
	
	private String password;
	
	private String about;
}
