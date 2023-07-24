package com.blog.app.BlogAppApi.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.BlogAppApi.Repository.CategoryRepository;
import com.blog.app.BlogAppApi.entity.Category;
import com.blog.app.BlogAppApi.exception.ResourceNotFoundException;
import com.blog.app.BlogAppApi.payloads.CategoryDto;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto craeteCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		String categoryId = UUID.randomUUID().toString();
		category.setCategoryId(categoryId);
		Category createCategory = this.categoryRepository.save(category);
		return this.modelMapper.map(createCategory,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(String categoryId, CategoryDto categoryDto) {
		
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->
		new ResourceNotFoundException("The data not present in dataabase table..."));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatecategory = this.categoryRepository.save(category);
		return this.modelMapper.map(updatecategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getSingleCategory(String categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->
		new ResourceNotFoundException("The Data not present in database Table..."));
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryRepository.findAll();
		List<CategoryDto> categorisDto = categories.stream().map((cate)->modelMapper.
				map(cate, CategoryDto.class)).collect(Collectors.toList());
		return categorisDto;
	}

}
