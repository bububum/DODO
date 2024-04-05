package com.example.Dodo.controller;

import com.example.Dodo.model.request.ProductCreateRequest;
import com.example.Dodo.service.ProductSizeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product_size")
public class ProductSizeController {

    private final ProductSizeService service;

    @PostMapping("/create/product")
    @Tag(name = "Create product")
    public ResponseEntity<?> create(@RequestBody ProductCreateRequest request, @RequestHeader Integer languageOrdinal) {
        return ResponseEntity.ok(service.create(request, languageOrdinal));
    }
}
