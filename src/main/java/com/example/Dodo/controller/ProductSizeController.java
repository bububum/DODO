package com.example.Dodo.controller;

import com.example.Dodo.model.request.ProductCreateRequest;
import com.example.Dodo.service.ProductSizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product_size")
public class ProductSizeController {

    private final ProductSizeService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductCreateRequest request, @RequestHeader Integer languageOrdinal) {
        return ResponseEntity.ok(service.create(request, languageOrdinal));
    }
}
