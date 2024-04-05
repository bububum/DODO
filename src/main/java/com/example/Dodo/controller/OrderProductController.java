package com.example.Dodo.controller;

import com.example.Dodo.model.request.OrderCreateRequest;
import com.example.Dodo.model.request.OrderRepeatRequest;
import com.example.Dodo.service.OrderProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order_product")
public class OrderProductController {

    private final OrderProductService service;

    @PostMapping("/create/order")
    @Tag(name = "Create order")
    public ResponseEntity<?> create(@RequestBody OrderCreateRequest request, @RequestHeader String accessToken, @RequestHeader Integer languageOrdinal) {
        return ResponseEntity.ok(service.create(request,languageOrdinal, accessToken));
    }

    @GetMapping("/get/history/order")
    @Tag(name = "Show history of orders")
    public ResponseEntity<?> historyOfOrder(@RequestHeader String accessToken, @RequestHeader Integer languageOrdinal, @RequestHeader Integer limit) {
        return ResponseEntity.ok(service.historyOfOrders(accessToken, languageOrdinal, limit));
    }

    @PostMapping("/repeat/order")
    @Tag(name = "Repeat order")
    public ResponseEntity<?> repeatOrder(@RequestHeader String accessToken, @RequestHeader Integer languageOrdinal,
                                         @RequestParam Long orderId) {
        return ResponseEntity.ok(service.repeatOrder(orderId, accessToken, languageOrdinal));
    }

}
