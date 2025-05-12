package org.example.authapplication.controller;

import org.example.authapplication.model.User;
import org.example.authapplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        return authService.signup(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return authService.login(user);
    }
}
