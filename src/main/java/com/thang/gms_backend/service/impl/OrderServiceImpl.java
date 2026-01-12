package com.thang.gms_backend.service.impl;

import com.thang.gms_backend.constant.ErrorCode;
import com.thang.gms_backend.dto.request.OrderRequest;
import com.thang.gms_backend.dto.response.OrderResponse;
import com.thang.gms_backend.entity.Customers;
import com.thang.gms_backend.entity.Measurements;
import com.thang.gms_backend.entity.TailoringOrders;
import com.thang.gms_backend.exception.AppException;
import com.thang.gms_backend.mapstruct.TailoringOrderMapper;
import com.thang.gms_backend.repository.CustomerRepository;
import com.thang.gms_backend.repository.MeasurementRepository;
import com.thang.gms_backend.repository.OrderRepository;
import com.thang.gms_backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final MeasurementRepository measurementRepository;
    private final TailoringOrderMapper tailoringOrderMapper;
    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        Customers customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));

        List<Measurements> measurements = measurementRepository.findAllById(request.getMeasurementIds());
        if (measurements.isEmpty()) {
            throw new AppException(ErrorCode.MEASUREMENT_REQUIRED);
        }

        // 3. Khởi tạo Order
        TailoringOrders order = new TailoringOrders();
        order.setNote(request.getNote());
        order.setReceivedDate(request.getReceivedDate());
        order.setCustomer(customer);
        order.setMeasurements(measurements);
        order = orderRepository.save(order);

        return tailoringOrderMapper.toOrderResponse(order);
    }

    @Override
    public OrderResponse updateOrder(String orderId, OrderRequest request) {
        TailoringOrders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        order.setNote(request.getNote());
        order.setReceivedDate(request.getReceivedDate());
        order = orderRepository.save(order);
        return tailoringOrderMapper.toOrderResponse(order);
    }

    @Override
    public Boolean deleteOrder(String orderId) {
        TailoringOrders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        orderRepository.delete(order);
        return true;
    }

    @Override
    public Page<OrderResponse> getAllOrders(int page, int size, String customerId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<TailoringOrders> orderPage;

        if (customerId != null && !customerId.isEmpty()) {
            orderPage = orderRepository.findByCustomerId(customerId, pageable);
        } else {
            orderPage = orderRepository.findAll(pageable);
        }

        return orderPage.map(tailoringOrderMapper::toOrderResponse);
    }

    @Override
    public OrderResponse getOrderDetails(String orderId) {
        TailoringOrders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        return tailoringOrderMapper.toOrderResponse(order);
    }
}
