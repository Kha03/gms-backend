package com.thang.gms_backend.service;

import com.thang.gms_backend.entity.Customers;
import com.thang.gms_backend.entity.Measurements;

import java.util.List;

public interface CustomerService {
    Customers saveCustomer(Customers customer);
    Customers getCustomerById(String id);
    List<Customers> getAllCustomersByUserId(String userId);
    void addMeasurement(String customerId, Measurements measurement);
}
