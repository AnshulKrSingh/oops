package com.anshul.spring.jpa.h2.controller;

public class PostRequest {
    private String postBody;
    private Long userId;
    

    // Constructors, getters, and setters

    public PostRequest() {
    }

    public PostRequest(String postBody, Long userId) {
        this.postBody = postBody;
        this.userId = userId;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}