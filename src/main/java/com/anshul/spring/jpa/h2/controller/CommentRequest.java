package com.anshul.spring.jpa.h2.controller;

public class CommentRequest {
    private String commentBody;
    private Long postId;
    private Long userId;

    // Constructors, getters, and setters
    // ...

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}