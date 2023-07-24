package com.blog.app.BlogAppApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.BlogAppApi.exception.ApiResponce;
import com.blog.app.BlogAppApi.payloads.CommentDto;
import com.blog.app.BlogAppApi.service.CommentServiceImpl;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentServiceImpl commentService;
	
	@PostMapping("/createComment")
	public ResponseEntity<CommentDto>createComment(
			@RequestBody CommentDto commentDto,
			@RequestParam("userId")String userId,
			@RequestParam("postId")String postId){
		CommentDto createComment = this.commentService.createComment(commentDto,userId, postId);
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/deleteComment/{commentId}")
	public ResponseEntity<ApiResponce>deleteComment(@PathVariable("commentId")String  commentId){
		
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("Deleted successfully",true,HttpStatus.OK),HttpStatus.OK);
	}

}
