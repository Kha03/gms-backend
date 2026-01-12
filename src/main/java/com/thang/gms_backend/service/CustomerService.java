package com.thang.gms_backend.service;

import com.thang.gms_backend.dto.request.CustomerRequest;
import com.thang.gms_backend.dto.response.CustomerResponse;
import com.thang.gms_backend.entity.Customers;
import com.thang.gms_backend.entity.Measurements;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    CustomerResponse saveCustomer(CustomerRequest customer);
    CustomerResponse updateCustomer(String id, CustomerRequest customer);
    CustomerResponse getCustomerById(String id);

    Page<CustomerResponse> searchCustomers(String keyword, int page, int size);

}
