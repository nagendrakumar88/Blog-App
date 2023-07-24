package com.blog.app.BlogAppApi.service;

import java.util.List;

import com.blog.app.BlogAppApi.payloads.CategoryDto;

public interface CategoryService {

	public CategoryDto craeteCategory(CategoryDto categoryDto);
	
	public CategoryDto updateCategory(String categoryId, CategoryDto categoryDto);
	
	public CategoryDto getSingleCategory(String categoryId);
	
	public List<CategoryDto>getAllCategory();
}
