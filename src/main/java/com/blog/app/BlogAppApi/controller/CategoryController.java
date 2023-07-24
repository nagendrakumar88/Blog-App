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

import com.blog.app.BlogAppApi.payloads.CategoryDto;
import com.blog.app.BlogAppApi.service.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryServiceImpl categoryService;

	@PostMapping("/createCategory")
	public ResponseEntity<CategoryDto>createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		CategoryDto craeteCategory = this.categoryService.craeteCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(craeteCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateCategory")
	public ResponseEntity<CategoryDto>updateCategory(
			@RequestParam("categoryId")String categoryId,@RequestBody CategoryDto categoryDto){
				
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryId, categoryDto);
		
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
		
	}
	
	@GetMapping("/getCategories")
	public ResponseEntity<List<CategoryDto>>getCategories(){
		List<CategoryDto> allCategory = this.categoryService.getAllCategory();
	return new ResponseEntity<List<CategoryDto>>(allCategory,HttpStatus.OK);
	}
	
	@GetMapping("/getCategory")
	public ResponseEntity<CategoryDto>getCategory(@RequestParam("categoryId")String categoryId){
		
		CategoryDto singleCategory = this.categoryService.getSingleCategory(categoryId);
	return new ResponseEntity<CategoryDto>(singleCategory,HttpStatus.OK);
	}
}
