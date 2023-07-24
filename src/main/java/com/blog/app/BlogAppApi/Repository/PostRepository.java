package com.blog.app.BlogAppApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blog.app.BlogAppApi.entity.Category;
import com.blog.app.BlogAppApi.entity.Post;
import com.blog.app.BlogAppApi.entity.User;

public interface PostRepository extends JpaRepository<Post, String>{

	List<Post>findByUser(User user);
	List<Post>findByCategory(Category category);
	
	@Query("select p from Post p where p.title like:key")
	List<Post>sreachByTitle(@Param("key") String title);
}
