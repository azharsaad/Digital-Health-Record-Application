package com.digitalrecord.app.controller;

import com.digitalrecord.app.dto.AuthResponse;
import com.digitalrecord.app.dto.LoginRequest;
import com.digitalrecord.app.dto.RegisterRequest;
import com.digitalrecord.app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    @Autowired
    public AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request)
    {
       return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register (@RequestBody RegisterRequest request)
    {
        AuthResponse response = authService.register(request);
        return  ResponseEntity.ok(response);
    }
}
