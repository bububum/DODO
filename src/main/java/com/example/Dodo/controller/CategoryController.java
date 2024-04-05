package com.example.Dodo.controller;

import com.example.Dodo.model.request.CategoryCreateRequest;
import com.example.Dodo.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService service;

    @PostMapping("/create/category")
    @Tag(name = "Create category")
    public ResponseEntity<?> create(@RequestBody CategoryCreateRequest request, @RequestHeader Integer languageOrdinal) {
        return ResponseEntity.ok(service.create(request, languageOrdinal));
    }
}
