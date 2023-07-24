package com.blog.app.BlogAppApi.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Post")
public class Post {

	@Id
	@Column(name = "Post ID")
	private String postId;
	@Column(name = "Title")
	private String title;
	@Column(name = "Content")
	private String content;
	@Column(name = "Image Name")
	private String imageName;
	@Column(name = "Added Date")
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn(name = "Category ID")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "User ID")
	private User user;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private Set<Comment>comments=new HashSet<>();
}
