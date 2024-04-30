package com.anshul.spring.jpa.h2.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anshul.spring.jpa.h2.model.Comment;
import com.anshul.spring.jpa.h2.model.CommentCreator;
import com.anshul.spring.jpa.h2.model.Post;
import com.anshul.spring.jpa.h2.model.User;
import com.anshul.spring.jpa.h2.repository.CommentRepository;
import com.anshul.spring.jpa.h2.repository.PostRepository;
import com.anshul.spring.jpa.h2.repository.UserRepository;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    

    // @GetMapping
    // public ResponseEntity<List<Comment>> getAllComments() {
    //     List<Comment> comments = commentRepository.findAll();
    //     return new ResponseEntity<>(comments, HttpStatus.OK);
    // }

    // @GetMapping("/comment")
    // public ResponseEntity<?> getCommentById(@RequestParam("commentId") Long commentId) {
    //     Optional<Comment> optionalComment = commentRepository.findById(commentId);
    //     if (optionalComment.isPresent()) {
    //         Comment comment = optionalComment.get();
    //         //CommentResponse commentResponse = new CommentResponse(comment.getCommentId(), comment.getCommentBody(), comment.getPost().getPostId(), comment.getCommentCreator());
    //         CommentResponse commentResponse = new CommentResponse();
    //         commentResponse.setCommentId(comment.getCommentId());
    //         commentResponse.setCommentBody(comment.getCommentBody());
    //         CommentCreator commentCreator = new CommentCreator();
    //         commentCreator.setName(comment.getCommentCreator().getName());
    //         commentCreator.setUserId(comment.getCommentCreator().getUserId());
    //         commentResponse.setCommentCreator(commentCreator);
    //         return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>("Comment not found", HttpStatus.NOT_FOUND);
    //     }
    // }
    @GetMapping("")
    public ResponseEntity<?> getCommentById(@RequestParam("commentId") Long commentId) {
    Optional<Comment> optionalComment = commentRepository.findById(commentId);
    if (optionalComment.isPresent()) {
        Comment comment = optionalComment.get();
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setCommentId(comment.getCommentId());
        commentResponse.setCommentBody(comment.getCommentBody());
        CommentCreator commentCreator = new CommentCreator();
        commentCreator.setName(comment.getCommentCreator().getName());
        commentCreator.setUserId(comment.getCommentCreator().getUserId());
        commentResponse.setCommentCreator(commentCreator);
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Comment does not exist", HttpStatus.NOT_FOUND);
    }
}

    @PostMapping("")
    public ResponseEntity<?> createComment(@RequestBody CommentRequest request) {
    // Find the user
    Optional<User> optionalUser = userRepository.findById(request.getUserId());
    if (!optionalUser.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist");
    }

    // Find the post
    Optional<Post> optionalPost = postRepository.findById(request.getPostId());
    if (!optionalPost.isPresent()) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("Error", "User does not exist");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
    }

    // Create a new comment
    Comment newComment = new Comment(request.getCommentBody(), optionalPost.get(), optionalUser.get());
    
    // Save the new comment
    commentRepository.save(newComment);
    
    return ResponseEntity.ok("Comment created successfully");
}

@PatchMapping
public ResponseEntity<?> updateComment(@RequestBody EditCommentRequest request) {
    // Find the comment
    Optional<Comment> optionalComment = commentRepository.findById(request.getCommentId());
    if (!optionalComment.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment does not exist");
    }

    // Update the comment body
    Comment existingComment = optionalComment.get();
    existingComment.setCommentBody(request.getCommentBody());
    // Update other fields as needed

    // Save the updated comment
    commentRepository.save(existingComment);

    return ResponseEntity.ok("Comment edited successfully");
}

@DeleteMapping("")
public ResponseEntity<?> deleteComment(@RequestParam("commentId") Long commentId) {
    try {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            commentRepository.deleteById(commentId);
            System.err.println("Comment deleted");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Comment does not exist", HttpStatus.NOT_FOUND);
        }
    } catch (Exception e) {
        return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}
