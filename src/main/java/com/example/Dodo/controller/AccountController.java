package com.example.Dodo.controller;

import com.example.Dodo.model.request.AuthRequest;
import com.example.Dodo.model.request.ValidateEmailRequest;
import com.example.Dodo.service.AccountService;
import com.example.Dodo.util.JwtProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService service;
    private final JwtProvider jwtProvider;

    @PostMapping("/auth")
    @Tag(name = "Auth account")
    ResponseEntity<?> authAccount(@RequestBody AuthRequest request, @RequestHeader Integer languageOrdinal){
        return ResponseEntity.ok(service.authAccount(request, languageOrdinal));
    }

    @PostMapping("/validate/account")
    @Tag(name = "Validate account")
    public ResponseEntity<?> validateAccount(@RequestBody ValidateEmailRequest request, @RequestHeader Integer languageOrdinal){
        return ResponseEntity.ok(service.validateAccount(request, languageOrdinal));
    }

    @GetMapping("/get/token")
    @Tag(name = "Get access token")
    public ResponseEntity<?> getAccessToken(@RequestParam Long userId) {
        return ResponseEntity.ok(jwtProvider.generateAccessToken(userId));
    }

    @GetMapping("/validate/token")
    @Tag(name = "Validate access token")
    public ResponseEntity<?> validate(@RequestHeader String token, @RequestHeader Integer languageOrdinal) {
        return ResponseEntity.ok(jwtProvider.validateToken(token, languageOrdinal));
    }

}
