package com.blog.app.BlogAppApi.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.app.BlogAppApi.Repository.CategoryRepository;
import com.blog.app.BlogAppApi.Repository.PostRepository;
import com.blog.app.BlogAppApi.Repository.UserRepository;
import com.blog.app.BlogAppApi.entity.Category;
import com.blog.app.BlogAppApi.entity.Post;
import com.blog.app.BlogAppApi.entity.User;
import com.blog.app.BlogAppApi.exception.ResourceNotFoundException;
import com.blog.app.BlogAppApi.payloads.CategoryDto;
import com.blog.app.BlogAppApi.payloads.PostDto;
import com.blog.app.BlogAppApi.payloads.PostResponce;

import jakarta.validation.ReportAsSingleViolation;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private  PostRepository postRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public PostDto createPost(PostDto postDto,String userId,String categoryId) {
		Post post = this.modelMapper.map(postDto, Post.class);
		User user = this.userRepository.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("User Not Found..."));
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()->
		new ResourceNotFoundException("Category not present in database..."));
		String postId = UUID.randomUUID().toString();
		post.setPostId(postId);
		post.setAddedDate(new Date());
		post.setImageName("default.png");
		post.setUser(user);
		post.setCategory(category);
		Post createdPost = this.postRepository.save(post);
		PostDto postDto2 = this.modelMapper.map(createdPost, PostDto.class);
		return postDto2;
	}

	@Override
	public PostDto updatePost(String postId, PostDto postDto) {
		Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("The data not present in database.."));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = this.postRepository.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public PostDto getPost(String postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("The data not present in database.."));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponce getPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		/*
		 * if(sortDir.equalsIgnoreCase("asc")) { sort= Sort.by(sortBy).ascending();
		 * }else { sort=Sort.by(sortBy).descending(); }
		 */
		Pageable pageble=PageRequest.of(pageNumber, pageSize,sort);
        Page<Post> pagePost = this.postRepository.findAll(pageble);
		List<Post> allPost = pagePost.getContent();
        List<PostDto> postDtos = allPost.stream().map(post->modelMapper.map(post,PostDto.class))
        .collect(Collectors.toList());
        
        PostResponce responce=new PostResponce();
        responce.setContent(postDtos);
        responce.setPageNumber(pagePost.getNumber());
        responce.setPageSize(pagePost.getSize());
        responce.setTotalElements(pagePost.getTotalElements());
        responce.setTotalPages(pagePost.getTotalPages());
        responce.setLastPage(pagePost.isLast());
        
		return responce;
	}

	@Override
	public List<PostDto> getPostByUser(String userId) {
	
		User user = this.userRepository.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("This Data not Present in the database Table.."));
		List<Post> posts = this.postRepository.findByUser(user);
		List<PostDto> postDtos = posts.stream().map(post->modelMapper.map(post, PostDto.class))
		.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByCategory(String CategoryId) {
		Category category = this.categoryRepository.findById(CategoryId).orElseThrow(()->
		new ResourceNotFoundException("The Data not Present..."));
		List<Post> posts = this.postRepository.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map(post->modelMapper.map(post, PostDto.class))
		.collect(Collectors.toList());		
		return postDtos;
		
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = this.postRepository.sreachByTitle("%"+keyword+"%");
		List<PostDto> postDtos = posts.stream().map(post->modelMapper.map(post, PostDto.class))
		.collect(Collectors.toList());
				
		return postDtos;
	}

	@Override
	public void deletePost(String postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("The data not present in database.."));
		this.postRepository.delete(post);
	}

}
