package com.anshul.spring.jpa.h2.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anshul.spring.jpa.h2.model.Comment;
import com.anshul.spring.jpa.h2.model.CommentCreator;
import com.anshul.spring.jpa.h2.model.Post;
import com.anshul.spring.jpa.h2.model.User;
import com.anshul.spring.jpa.h2.repository.PostRepository;
import com.anshul.spring.jpa.h2.repository.UserRepository;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    // @GetMapping
    // public ResponseEntity<List<Post>> getAllPosts() {
    //     List<Post> posts = postRepository.findAll();
    //     return new ResponseEntity<>(posts, HttpStatus.OK);
    // }

    @GetMapping("")
public ResponseEntity<?> getPostById(@RequestParam("postId") Long postId) {
    Optional<Post> optionalPost = postRepository.findById(postId);
    if (optionalPost.isPresent()) {
        Post post = optionalPost.get();
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : post.getComments()) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setCommentId(comment.getCommentId());
            commentResponse.setCommentBody(comment.getCommentBody());
            CommentCreator commentCreator = new CommentCreator();
            commentCreator.setName(comment.getCommentCreator().getName());
            commentCreator.setUserId(comment.getCommentCreator().getUserId());
            commentResponse.setCommentCreator(commentCreator);
            commentResponses.add(commentResponse);
        }
        PostResponse postResponse = new PostResponse();
        postResponse.setPostId(post.getPostId());
        postResponse.setPostBody(post.getPostBody());
        postResponse.setDate(post.getDate());
        postResponse.setComments(commentResponses);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Post does not exist", HttpStatus.NOT_FOUND);
    }
}
    @PostMapping
public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
    try {
        // ...

        // Find the user by ID
        Optional<User> optionalUser = userRepository.findById(postRequest.getUserId());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Create a new Post entity
        User user = optionalUser.get();
        Post post = new Post(postRequest.getPostBody(), LocalDate.now());
        post.setUser(user); // Set the user for the post

        // Save the post
        postRepository.save(post);

        return new ResponseEntity<>("Post Creation Successful", HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    // @PatchMapping("/{postId}")
    // public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody Post post) {
    //     try {
    //         Optional<Post> optionalPost = postRepository.findById(postId);
    //         if (optionalPost.isPresent()) {
    //             Post existingPost = optionalPost.get();
    //             existingPost.setPostBody(post.getPostBody());
    //             existingPost.setUser(post.getUser());
    //             existingPost.setDate(post.getDate());
    //             postRepository.save(existingPost);
    //             return new ResponseEntity<>(existingPost, HttpStatus.OK);
    //         } else {
    //             return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);
    //         }
    //     } catch (Exception e) {
    //         return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
    @PatchMapping
    public ResponseEntity<?> editPost(@RequestBody EditPostRequest request) {
        Long postId = request.getPostId(); // Assuming PostRequest has a method to get postId as Long
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setPostBody(request.getPostBody());
            postRepository.save(post);
            return ResponseEntity.ok("Post edited successfully");
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Error", "Post does not exist");
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            return ResponseEntity.ok("Post does not exist");
        }
    }
    


    @DeleteMapping("")
public ResponseEntity<?> deletePost(@RequestParam("postId") Long postId) {
    try {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            postRepository.deleteById(postId);
            return ResponseEntity.ok("Post deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post does not exist");
        }
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting post: " + e.getMessage());
    }
}
}
