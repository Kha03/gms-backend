package com.thang.gms_backend.repository;

import com.thang.gms_backend.entity.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurements, String> {
}
