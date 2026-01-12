package com.thang.gms_backend.service;

import com.thang.gms_backend.dto.response.MeasurementResponse;
import com.thang.gms_backend.entity.Measurements;

import java.util.List;

public interface MeasurementService {
    MeasurementResponse addMeasurements(String customerId, Measurements measurement);
    MeasurementResponse updateMeasurements(String measurementId, String labelNew);

    List<MeasurementResponse> getMeasurementsByCustomerId(String customerId);
}
