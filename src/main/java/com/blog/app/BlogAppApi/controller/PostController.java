package com.blog.app.BlogAppApi.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.app.BlogAppApi.config.AppConstant;
import com.blog.app.BlogAppApi.exception.ApiResponce;
import com.blog.app.BlogAppApi.payloads.PostDto;
import com.blog.app.BlogAppApi.payloads.PostResponce;
import com.blog.app.BlogAppApi.service.FileService;
import com.blog.app.BlogAppApi.service.PostServiceImpl;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.Delegate;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostServiceImpl postService;
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	
	@PostMapping("/user/category/createPost")
	public ResponseEntity<PostDto>createPost(
			
			@RequestBody PostDto postDto, 
			@RequestParam("userId")String userId,
			@RequestParam("categoryId")String categoryId){
    
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/updatePost")
	public ResponseEntity<PostDto>updatePost(@RequestParam("postId")String postId,@RequestBody PostDto postDto){
		PostDto updatePost = this.postService.updatePost(postId, postDto);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	@GetMapping("/user/post")
	public ResponseEntity<List<PostDto>>getPostByUser(@RequestParam("userId")String userId){
		List<PostDto> posts = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}

	@GetMapping("/category/post")
	public ResponseEntity<List<PostDto>>getPostByCategory(
			@RequestParam("categoryId")String categoryId){
   
		List<PostDto> categories = this.postService.getPostByCategory(categoryId);		
		return new ResponseEntity<List<PostDto>>(categories,HttpStatus.OK);
		
	}
	
	@GetMapping("/getPosts")
	public ResponseEntity<PostResponce> getPosts(
			@RequestParam(value = "pageNumber", defaultValue =AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
		    @RequestParam(value = "pageSize",defaultValue =AppConstant.PAGE_SIZE,required = false)Integer pageSize,
		    @RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY)String sortBy,
	        @RequestParam(value = "sortDir",defaultValue =AppConstant.SORT_DIR,required = false)String sortDir){
		PostResponce postResponce = this.postService.getPosts(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponce>(postResponce,HttpStatus.OK);
		
	}
	
	@GetMapping("/getPost")
	public ResponseEntity<PostDto> getPost(@RequestParam("postId")String postId) {
		PostDto post = this.postService.getPost(postId);		
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);		
	}
	
	@DeleteMapping("/deletePost")
	public ResponseEntity<ApiResponce>deletePost(@RequestParam("postId")String postId){
		
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponce>(HttpStatus.OK);
	}
	
	@GetMapping("/search/post/{keywords}")
	public ResponseEntity<List<PostDto>>searchPost(
			@PathVariable("keywords")String keywords){
		
		List<PostDto> searchPosts = this.postService.searchPost(keywords);
			return new ResponseEntity<List<PostDto>>(searchPosts,HttpStatus.OK);
		
	}
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto>uploadImae(
			@RequestParam("image")MultipartFile image,
	        @PathVariable("postId")String postId) throws IOException{
		
		PostDto postDto = this.postService.getPost(postId);
		
		String fileName = this.fileService.uploadImage(path, image);
	
		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postId, postDto);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	@GetMapping(value = "/post/image/{imageName}",produces =MediaType.IMAGE_JPEG_VALUE )
	public void downloadImage(
			@RequestParam("imageName")String imageName,HttpServletResponse responce) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		responce.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, responce.getOutputStream());
	}
}