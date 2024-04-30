package com.anshul.spring.jpa.h2.controller;

import com.anshul.spring.jpa.h2.model.CommentCreator;

public class CommentResponse {
    private Long commentId;
    private String commentBody;
    private CommentCreator commentCreator;

    public CommentCreator getCommentCreator() {
        return commentCreator;
    }

    public void setCommentCreator(CommentCreator commentCreator) {
        this.commentCreator = commentCreator;
    }

    // // Constructors
    // public CommentResponse(Long commentId, String commentBody, Long userId,String name) {
    //     this.commentId = commentId;
    //     this.commentBody = commentBody;
    //     this.userId = userId;
    //     this.name = name;
    // }

    
    // Getters and Setters
    public Long getCommentId() {
        return commentId;
    }
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    public String getCommentBody() {
        return commentBody;
    }
    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }
    }
//     public Long getCommentId() {
//         return commentId;
//     }

//     public void setCommentId(Long commentId) {
//         this.commentId = commentId;
//     }

//     public String getCommentBody() {
//         return commentBody;
//     }

//     public void setCommentBody(String commentBody) {
//         this.commentBody = commentBody;
//     }

//     public void setUserId(Long userId2) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'setUserId'");
//     }

//     // public UserResponse getCommentCreator() {
//     //     return commentCreator;
//     // }

//     // public void setCommentCreator(UserResponse commentCreator) {
//     //     this.commentCreator = commentCreator;
//     // }