package com.blog.app.BlogAppApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHendller {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public static ResponseEntity<ApiResponce> globalexceptionHendle(ResourceNotFoundException ex) {
		
		String message = ex.getMessage();
		ApiResponce apiResponce = ApiResponce.builder().message(message).success(false).status(HttpStatus.NOT_FOUND).build();
		
		return new ResponseEntity<ApiResponce>(apiResponce,HttpStatus.OK);
		
	}

}
