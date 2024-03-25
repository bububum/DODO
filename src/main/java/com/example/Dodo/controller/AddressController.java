package com.example.Dodo.controller;

import com.example.Dodo.model.request.AddressCreateRequest;
import com.example.Dodo.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AddressCreateRequest request, @RequestHeader Integer languageOrdinal, @RequestHeader String token) {
        return ResponseEntity.ok(service.create(request, languageOrdinal, token));
    }
}
