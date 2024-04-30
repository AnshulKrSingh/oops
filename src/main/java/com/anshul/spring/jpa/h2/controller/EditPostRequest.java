package com.anshul.spring.jpa.h2.controller;

public class EditPostRequest {
    private Long postId;
    private String postBody;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }
}
