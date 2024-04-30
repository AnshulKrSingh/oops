package com.anshul.spring.jpa.h2.controller;
import java.time.LocalDate;
import java.util.List;


public class PostResponse {
    private Long postId;
    private String postBody;
    private LocalDate date;
    private List<CommentResponse> comments;

    // Constructors
    // public PostResponse(Long postId, String postBody, LocalDate date, List<CommentResponse> comments) {
    //     this.postId = postId;
    //     this.postBody = postBody;
    //     this.date = date;
    //     this.comments = comments;
    // }

    // Getters and Setters
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }
}
