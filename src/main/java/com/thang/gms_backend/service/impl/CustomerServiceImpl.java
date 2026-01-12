package com.thang.gms_backend.service.impl;

import com.thang.gms_backend.constant.ErrorCode;
import com.thang.gms_backend.dto.request.CustomerRequest;
import com.thang.gms_backend.dto.response.CustomerResponse;
import com.thang.gms_backend.entity.Customers;
import com.thang.gms_backend.entity.Users;
import com.thang.gms_backend.exception.AppException;
import com.thang.gms_backend.mapstruct.CustomerMapper;
import com.thang.gms_backend.repository.CustomerRepository;
import com.thang.gms_backend.repository.UserRepository;
import com.thang.gms_backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
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
    public CustomerResponse updateCustomer(String id, CustomerRequest customer) {
        Customers existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));
        // Kiểm tra nếu số điện thoại mới khác với số hiện tại
        if (!existingCustomer.getPhone().equals(customer.getPhone())) {
            // Kiểm tra trùng lặp số điện thoại
            if (customerRepository.existsByPhone(customer.getPhone())) {
                throw new AppException(ErrorCode.PHONE_ALREADY_EXISTS);
            }
            existingCustomer.setPhone(customer.getPhone());
        }
        existingCustomer.setFullName(customer.getFullName());
        existingCustomer = customerRepository.save(existingCustomer);
        return customerMapper.toCustomerResponse(existingCustomer);
    }

    @Override
        public CustomerResponse getCustomerById (String id){
            Customers customer = customerRepository.findById(id)
                    .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND));
            return customerMapper.toCustomerResponse(customer);
        }

    @Override
    public Page<CustomerResponse> searchCustomers(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("fullName").ascending());
        Page<Customers> customerPage;
        if (keyword == null || keyword.trim().isEmpty()) {
            // Nếu không có từ khóa, trả về tất cả
            customerPage = customerRepository.findAll(pageable);
        } else {
            // Tìm kiếm theo tên hoặc số điện thoại (truyền keyword vào cả 2 tham số)
            customerPage = customerRepository.findByFullNameContainingIgnoreCaseOrPhoneContaining(
                    keyword, keyword, pageable);
        }
        return customerPage.map(customerMapper::toCustomerResponse);
    }


    }
