package com.blog.app.BlogAppApi.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog.app.BlogAppApi.entity.Category;
import com.blog.app.BlogAppApi.entity.Comment;
import com.blog.app.BlogAppApi.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private String postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto>comments=new HashSet<>();
}
