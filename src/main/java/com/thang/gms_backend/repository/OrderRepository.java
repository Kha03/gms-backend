package com.thang.gms_backend.repository;

import com.thang.gms_backend.entity.TailoringOrders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<TailoringOrders, String> {
    Page<TailoringOrders> findByCustomerId(String customerId, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"customer", "measurements"})
    Optional<TailoringOrders> findById(String id);
}
