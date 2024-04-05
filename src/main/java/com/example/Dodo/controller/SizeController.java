package com.example.Dodo.controller;

import com.example.Dodo.model.request.SizeCreateRequest;
import com.example.Dodo.service.SizeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/size")
public class SizeController {

    private final SizeService service;

    @PostMapping("/create/size")
    @Tag(name = "Create size")
    public ResponseEntity<?> create(@RequestBody SizeCreateRequest request, @RequestHeader Integer languageOrdinal) {
        return ResponseEntity.ok(service.create(request, languageOrdinal));
    }
}
