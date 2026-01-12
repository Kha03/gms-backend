package com.thang.gms_backend.repository;

import com.thang.gms_backend.entity.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurements, String> {
    List<Measurements> findByCustomerId(String customerId);
}
