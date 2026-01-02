package com.thang.gms_backend.service.impl;

import com.thang.gms_backend.constant.ErrorCode;
import com.thang.gms_backend.dto.request.CustomerRequest;
import com.thang.gms_backend.dto.response.CustomerResponse;
import com.thang.gms_backend.entity.Customers;
import com.thang.gms_backend.entity.Measurements;
import com.thang.gms_backend.entity.Users;
import com.thang.gms_backend.exception.AppException;
import com.thang.gms_backend.mapstruct.CustomerMapper;
import com.thang.gms_backend.repository.CustomerRepository;
import com.thang.gms_backend.repository.MeasurementRepository;
import com.thang.gms_backend.repository.UserRepository;
import com.thang.gms_backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final MeasurementRepository measurementRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse saveCustomer(CustomerRequest customerRequest) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        // 2. Tìm User trong DB
        Users currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        // 3. Kiểm tra trùng lặp
        if (customerRepository.existsByPhone(customerRequest.getPhone())) {
            throw new AppException(ErrorCode.PHONE_ALREADY_EXISTS);
        }
        // 4. Tạo Entity và gán mối quan hệ
        Customers customer = new Customers();
        customer.setFullName(customerRequest.getFullName());
        customer.setPhone(customerRequest.getPhone());
        customer.setUser(currentUser); //  Kết nối khách hàng với chủ sở hữu
        customer = customerRepository.save(customer);
            // 3. Nếu mọi thứ ổn, tiến hành lưu
            return customerMapper.toCustomerResponse(customer);
        }

        @Override
        public Customers getCustomerById (String id){
            return null;
        }

        @Override
        public List<Customers> getAllCustomersByUserId (String userId){
            return null;
        }

        @Override
        public void addMeasurement (String customerId, Measurements measurement){

        }
    }
