package com.example.Dodo.controller;

import com.example.Dodo.model.request.UserCreateRequest;
import com.example.Dodo.service.UserService;
import com.example.Dodo.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService service;
    private final JwtProvider jwtProvider;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserCreateRequest request, @RequestHeader Integer languageOrdinal) {
        return ResponseEntity.ok(service.create(request, languageOrdinal));
    }

    @GetMapping("/auth")
    public ResponseEntity<?> auth(@RequestParam Long userId) {
        return ResponseEntity.ok(jwtProvider.generateAccessToken(userId));
    }

    @GetMapping("/validate/token")
    public ResponseEntity<?> validate(@RequestHeader String token) {
        return ResponseEntity.ok(jwtProvider.validateToken(token));
    }
}
