package com.anshul.spring.jpa.h2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anshul.spring.jpa.h2.model.User;
import com.anshul.spring.jpa.h2.repository.UserRepository;
import java.util.stream.Collectors;

@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    // @GetMapping
    // public ResponseEntity<List<User>> getAllUsers() {
    //     List<User> users = userRepository.findAll();
    //     return new ResponseEntity<>(users, HttpStatus.OK);
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            if (!users.isEmpty()) {
                List<UserResponse> userResponses = users.stream()
                        .map(user -> new UserResponse(user.getUserId(), user.getName(), user.getEmail())).collect(Collectors.toList());
                return new ResponseEntity<>(userResponses, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No users found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while fetching all users", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/user/{userId}")
public ResponseEntity<?> getUserById(@PathVariable Long userId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        UserResponse userResponse = new UserResponse(user.getUserId(), user.getName(), user.getEmail());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    } else {
        return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
    }
}

    @PostMapping("/user/signup")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            // Check if user with the same email already exists
            Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                return new ResponseEntity<>("Forbidden, Account already exists ", HttpStatus.BAD_REQUEST);
            }
            
            // Save the new user if it doesn't exist
            userRepository.save(user);
            return new ResponseEntity<>("Account Creation Successful", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            // Find the user by email
            Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                // Check if the password matches
                if (existingUser.get().getPassword().equals(user.getPassword())) {
                    return new ResponseEntity<>("Login Successful", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Username/Password Incorrect", HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>("User does not exist", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PutMapping("/{userId}")
    // public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody User user) {
    //     try {
    //         Optional<User> optionalUser = userRepository.findById(userId);
    //         if (optionalUser.isPresent()) {
    //             User existingUser = optionalUser.get();
    //             existingUser.setEmail(user.getEmail());
    //             existingUser.setName(user.getName());
    //             existingUser.setPassword(user.getPassword());
    //             userRepository.save(existingUser);
    //             return new ResponseEntity<>(existingUser, HttpStatus.OK);
    //         } else {
    //             return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    //         }
    //     } catch (Exception e) {
    //         return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    // @DeleteMapping("/{userId}")
    // public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
    //     try {
    //         Optional<User> optionalUser = userRepository.findById(userId);
    //         if (optionalUser.isPresent()) {
    //             userRepository.deleteById(userId);
    //             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //         } else {
    //             return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    //         }
    //     } catch (Exception e) {
    //         return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }
}
