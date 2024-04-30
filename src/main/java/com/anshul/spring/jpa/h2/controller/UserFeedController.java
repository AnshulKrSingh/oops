// package com.anshul.spring.jpa.h2.controller;


// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.anshul.spring.jpa.h2.model.User;
// import com.anshul.spring.jpa.h2.repository.UserRepository;


// @RestController
// @RequestMapping("")
// public class UserFeedController {
    
//     UserRepository userRepository;
//     @GetMapping("/")
//     public String getMethodName(@RequestParam String param) {
//         return new String();
//     }
//     @GetMapping("/users")
//     public ResponseEntity<?> getAllUsers() {
//         try {
//             List<User> users = userRepository.findAll();
//             if (!users.isEmpty()) {
//                 List<UserResponse> userResponses = users.stream()
//                         .map(user -> new UserResponse(user.getUserId(), user.getName(), user.getEmail())).collect(Collectors.toList());
//                 return new ResponseEntity<>(userResponses, HttpStatus.OK);
//             } else {
//                 return new ResponseEntity<>("No users found", HttpStatus.NOT_FOUND);
//             }
//         } catch (Exception e) {
//             return new ResponseEntity<>("Error occurred while fetching all users", HttpStatus.INTERNAL_SERVER_ERROR);
//         }
//     }
// }


