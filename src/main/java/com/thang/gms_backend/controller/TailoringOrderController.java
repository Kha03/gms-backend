package com.thang.gms_backend.controller;

import com.thang.gms_backend.dto.request.OrderRequest;
import com.thang.gms_backend.dto.response.OrderResponse;
import com.thang.gms_backend.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tailoring-orders")
@Tag(name = "Tailoring Order Management", description = "Quản lý đơn hàng may đo.")
@SecurityRequirement(name = "bearerAuth")
public class TailoringOrderController {
    private final OrderService orderService;
    @Operation(summary = "Create Tailoring Order")
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @Operation(summary = "Update Tailoring Order")
    @PostMapping("/update/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable String id, @Valid @RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.updateOrder(id, request));
    }

    @Operation(summary = "Delete Tailoring Order")
    @PostMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable String id) {
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }// API: GET /api/orders/customer/{customerId}?page=0&size=5

    @Operation(summary = "Get All Orders with Pagination and Optional Customer Filter")
    @GetMapping
    public ResponseEntity<Page<OrderResponse>> getOrders(
            @RequestParam(required = false) String customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(orderService.getAllOrders(page, size, customerId));
    }
    @Operation(summary = "Xem chi tiết đơn hàng và các số đo đi kèm")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable String orderId) {
        return ResponseEntity.ok(orderService.getOrderDetails(orderId));
    }
}
