package com.thang.gms_backend.service;

import com.thang.gms_backend.dto.request.OrderRequest;
import com.thang.gms_backend.dto.response.OrderResponse;
import org.springframework.data.domain.Page;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request);

    OrderResponse updateOrder(String orderId, OrderRequest request);

    Boolean deleteOrder(String orderId);
    Page<OrderResponse> getAllOrders(int page, int size, String CustomerId);
    OrderResponse getOrderDetails(String orderId);
}
