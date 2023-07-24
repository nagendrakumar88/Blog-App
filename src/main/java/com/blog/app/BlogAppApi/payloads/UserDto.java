package com.blog.app.BlogAppApi.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
	
	@NotEmpty
	@Size(min = 4,message = "User Name must be minimum Four character...")
	private String name;
	
	@Email(message = "Email address is not valid ...")
	private String email;
	
	@NotEmpty
	@Size(min = 4,max = 8,message = "Please Enter valid password min 4 and max 8 digit and char..")
	private String password;
	
	@NotEmpty
	private String about;
}
