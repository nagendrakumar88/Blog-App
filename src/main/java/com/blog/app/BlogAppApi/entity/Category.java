package com.blog.app.BlogAppApi.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Category")
public class Category {

	@Id
	@Column(name = "Cotegory ID")
	private String categoryId;
	
	@Column(name = "Categkory Title",length = 100,nullable = false)
	private String categoryTitle;
	
	@Column(name = "Category Description")
	private String categoryDescription;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Post>post=new ArrayList<>();
}
