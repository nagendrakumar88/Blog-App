package com.blog.app.BlogAppApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.BlogAppApi.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
