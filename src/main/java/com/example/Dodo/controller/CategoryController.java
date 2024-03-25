package com.example.Dodo.controller;

import com.example.Dodo.model.request.CategoryCreateRequest;
import com.example.Dodo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryCreateRequest request, @RequestHeader Integer languageOrdinal) {
        return ResponseEntity.ok(service.create(request, languageOrdinal));
    }
}
