package com.blog.app.BlogAppApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.BlogAppApi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String>{

}
