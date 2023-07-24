package com.blog.app.BlogAppApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.BlogAppApi.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, String>{

}
