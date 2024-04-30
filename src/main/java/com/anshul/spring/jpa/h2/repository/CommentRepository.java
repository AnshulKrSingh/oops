package com.anshul.spring.jpa.h2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anshul.spring.jpa.h2.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
