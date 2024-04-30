package com.anshul.spring.jpa.h2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anshul.spring.jpa.h2.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findByPostBodyContainingIgnoreCase(String postBody);
}
