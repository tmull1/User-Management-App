package com.example.User.Management.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to register a user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid User user) {
        userService.saveUserWithRole(user, user.getRole());
        return ResponseEntity.ok("User registered successfully");
    }

    // Endpoint to get all users (Admin access only)
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Endpoint to get user profile (Authenticated users)
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile() {
        // Get the currently authenticated user's username
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Find the user by username
        Optional<User> currentUserOpt = userService.findByUsername(currentUsername);

        // Return the user details if found
        return currentUserOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }
}




