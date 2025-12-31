package com.thang.gms_backend.service.impl;

import com.thang.gms_backend.constant.ErrorCode;
import com.thang.gms_backend.entity.Customers;
import com.thang.gms_backend.entity.Measurements;
import com.thang.gms_backend.exception.AppException;
import com.thang.gms_backend.repository.CustomerRepository;
import com.thang.gms_backend.repository.MeasurementRepository;
import com.thang.gms_backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final MeasurementRepository measurementRepository;
    @Override
    public Customers saveCustomer(Customers customer) {
        // 1. Kiểm tra trùng lặp
        if (customer.getId() == null) {
            if (customerRepository.existsByPhone(customer.getPhone())) {
                throw new AppException(ErrorCode.PHONE_ALREADY_EXISTS);
            }
        } else {
            // TRƯỜNG HỢP CẬP NHẬT: Check xem SĐT đã thuộc về người KHÁC chưa
            if (customerRepository.existsByPhoneAndIdNot(customer.getPhone(), customer.getId())) {
                throw new RuntimeException("Số điện thoại này đã được sử dụng bởi một khách hàng khác!");
            }
        }
        // 3. Nếu mọi thứ ổn, tiến hành lưu
        return customerRepository.save(customer);
    }
    @Override
    public Customers getCustomerById(String id) {
        return null;
    }

    @Override
    public List<Customers> getAllCustomersByUserId(String userId) {
        return null;
    }

    @Override
    public void addMeasurement(String customerId, Measurements measurement) {

    }
}
