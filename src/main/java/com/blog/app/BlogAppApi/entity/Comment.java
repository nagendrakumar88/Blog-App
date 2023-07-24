package com.blog.app.BlogAppApi.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Comment")
public class Comment {

	@Id
	
	@Column(name = "Comment ID")
	private String commId;
	
	@Column(name="Comment")
	
	private String content;
	
	@ManyToOne
	private Post post;
	
	@ManyToOne
	private User user;
	
}
