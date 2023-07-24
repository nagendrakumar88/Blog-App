package com.blog.app.BlogAppApi.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.BlogAppApi.Repository.CommentRepository;
import com.blog.app.BlogAppApi.Repository.PostRepository;
import com.blog.app.BlogAppApi.Repository.UserRepository;
import com.blog.app.BlogAppApi.entity.Comment;
import com.blog.app.BlogAppApi.entity.Post;
import com.blog.app.BlogAppApi.entity.User;
import com.blog.app.BlogAppApi.exception.ResourceNotFoundException;
import com.blog.app.BlogAppApi.payloads.CommentDto;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, String userId,String postId) {
		
		String commentId = UUID.randomUUID().toString();
		User user = this.userRepository.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("The Data not Present in database..."));
		Post post = this.postRepository.findById(postId).orElseThrow(()->
		new ResourceNotFoundException("The Data not Present in database..."));
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setCommId(commentId);
		comment.setUser(user);
		comment.setPost(post);
		Comment savedComment = this.commentRepository.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(String commentId) {
		Comment comment = this.commentRepository.findById(commentId).orElseThrow(()->
		new ResourceNotFoundException("The data Not present in database ..."));
		this.commentRepository.delete(comment);
	}

}
