package com.ironhack.pharmacyapi.controller;

import com.ironhack.pharmacyapi.dto.OrderDto;
import com.ironhack.pharmacyapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        var result = orderService.createOrder(orderDto);
        return ResponseEntity.created(URI.create("api/v1/orders/" + result.getId())).body(result);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}