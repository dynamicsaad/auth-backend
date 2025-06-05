package org.example.authapplication.service;

import org.example.authapplication.model.User;
import org.example.authapplication.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> signup(User user) {
        // Check if user already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("User with this email already exists");
        }
        
        // No password encoding - VERY UNSAFE
        userRepository.save(user);
        return ResponseEntity.ok("Signup successful!");
    }

    public ResponseEntity<String> login(User user) {
        User dbUser = userRepository.findByEmail(user.getEmail());
        if (dbUser != null && user.getPassword().equals(dbUser.getPassword())) {
            return ResponseEntity.ok("Login successful!");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
