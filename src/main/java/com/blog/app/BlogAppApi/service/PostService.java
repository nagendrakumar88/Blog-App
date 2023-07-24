package com.blog.app.BlogAppApi.service;


import java.util.List;

import com.blog.app.BlogAppApi.payloads.PostDto;
import com.blog.app.BlogAppApi.payloads.PostResponce;

public interface PostService {

	public PostDto createPost(PostDto postDto,String userId,String categoryId);
	
	public PostDto updatePost(String postId, PostDto postDto);
	
	public PostDto getPost(String postId);
	
	public PostResponce getPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	public List<PostDto>getPostByUser(String userId);
	
	public List<PostDto>getPostByCategory(String CategoryId);
	
	public List<PostDto>searchPost(String keyword);
	
	public void deletePost(String postId);

}
