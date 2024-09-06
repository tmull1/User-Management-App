package com.example.User.Management.App;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Save and encode the user password before saving
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Method to save user with a specific role (USER or ADMIN)
    public User saveUserWithRole(User user, Role role) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);  // Assign the role (USER or ADMIN)
        return userRepository.save(user);
    }

    // Retrieve all users from the repository
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Fetch a user by their ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Find a user by their username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Delete a user by their ID
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    // Update user details (if found)
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword())); // Encode the updated password
            user.setRole(updatedUser.getRole());
            return userRepository.save(user);
        }).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}











