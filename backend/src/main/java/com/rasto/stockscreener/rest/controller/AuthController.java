package com.rasto.stockscreener.rest.controller;

import com.rasto.stockscreener.rest.request.LoginRequest;
import com.rasto.stockscreener.rest.response.AuthResponse;
import com.rasto.stockscreener.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        boolean success = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (success) {
            return ResponseEntity.ok(new AuthResponse("User successfully logged in."));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Bad username or password."));
        }
    }
}
