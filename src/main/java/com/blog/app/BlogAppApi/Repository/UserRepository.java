package com.blog.app.BlogAppApi.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.BlogAppApi.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

	Optional<User>findByEmail(String email);
}
