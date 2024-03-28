package com.example.Dodo.controller;

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

    @PostMapping("/send/ad")
    public ResponseEntity<?> sendAd(Long userId) {
        return ResponseEntity.ok(service.sendAd(userId));
    }

}
