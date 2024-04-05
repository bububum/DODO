package com.example.Dodo.controller;

import com.example.Dodo.model.request.ProductCreateRequest;
import com.example.Dodo.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService service;

    @Tag(name = "Filter products")
    @GetMapping("/filter")
    public ResponseEntity<?> getProductByFilter(@RequestParam(required = false) Long sizeId,
                                      @RequestParam(required = false) BigDecimal fromPrice,
                                      @RequestParam(required = false) BigDecimal toPrice,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) Long categoryId) {

        return ResponseEntity.ok(service.getProductByFilter(sizeId, fromPrice, toPrice, name, categoryId));
    }

}
