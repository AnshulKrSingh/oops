package com.anshul.spring.jpa.h2.controller;

public class CommentCreatorResponse {
    private Long userID;
    private String name;

    // Constructors
    public CommentCreatorResponse(Long userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    // Getters and Setters
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
