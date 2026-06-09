package com.example.backendprojektjava.controller;

import com.example.backendprojektjava.dto.LoginRequest;
import com.example.backendprojektjava.dto.LoginResponse;
import com.example.backendprojektjava.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        if (request.getUsername().equals("user") && request.getPassword().equals("1234")) {
            String token = jwtUtil.generateToken("user", "USER");
            return ResponseEntity.ok(new LoginResponse(token));
        }

        if (request.getUsername().equals("admin") && request.getPassword().equals("admin123")) {
            String token = jwtUtil.generateToken("admin", "ADMIN");
            return ResponseEntity.ok(new LoginResponse(token));
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Fel användarnamn eller lösenord.");
    }
}