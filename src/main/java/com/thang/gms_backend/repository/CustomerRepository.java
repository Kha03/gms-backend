package com.thang.gms_backend.repository;

import com.thang.gms_backend.entity.Customers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, String> {
    Boolean existsByPhone(String phone);

    Boolean existsByPhoneAndIdNot(String phone, String id);
    Page<Customers> findByFullNameContainingIgnoreCaseOrPhoneContaining(
            String fullName, String phoneNumber, Pageable pageable);
}
