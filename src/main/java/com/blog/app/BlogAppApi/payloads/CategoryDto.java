package com.blog.app.BlogAppApi.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	private String categoryId;
	
	@NotEmpty
	@Size(min = 5,max = 50,message = "Please enter 5 to 20 carecter..")
	private String categoryTitle;
	
	@NotEmpty
	@Size(min = 10, max = 500,message = "Plese enter vailide description..")
	private String categoryDescription;

}
