package com.blog.app.BlogAppApi.service;

import com.blog.app.BlogAppApi.payloads.CommentDto;

public interface CommentService {

	public CommentDto createComment(CommentDto commentDto,String userId,String postId);
	
	public void deleteComment(String commentId);
}
