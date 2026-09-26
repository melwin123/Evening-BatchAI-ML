package com.amc.bfsi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.amc.bfsi.dto.LoginRequest;
import com.amc.bfsi.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173") // Allow Vite frontend
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        boolean isValid = authService.validateCredentials(request.getUsername(), request.getPassword());
        
        if (isValid) {
            // Return a dummy success token or response
            return ResponseEntity.ok().body("{\"token\": \"dummy-jwt-token-12345\"}");
        } else {
            return ResponseEntity.status(401).body("{\"message\": \"Invalid username or password\"}");
        }
    }
}