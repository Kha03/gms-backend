package com.thang.gms_backend.repository;

import com.thang.gms_backend.constant.OrderStatus;
import com.thang.gms_backend.entity.TailoringOrders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<TailoringOrders, String> {
    Page<TailoringOrders> findByCustomerId(String customerId, Pageable pageable);
    @Query("SELECT o FROM TailoringOrders o WHERE (:status IS NULL OR o.status = :status)")
    Page<TailoringOrders> findByStatus(@Param("status") OrderStatus status, Pageable pageable);
    @Override
    @EntityGraph(attributePaths = {"customer", "measurements"})
    Optional<TailoringOrders> findById(String id);
}
