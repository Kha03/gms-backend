package com.thang.gms_backend.repository;

import com.thang.gms_backend.entity.TailoringOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<TailoringOrders, String> {
}
