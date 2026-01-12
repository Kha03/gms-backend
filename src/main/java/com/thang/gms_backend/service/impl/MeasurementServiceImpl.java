package com.thang.gms_backend.service.impl;

import com.thang.gms_backend.constant.ErrorCode;
import com.thang.gms_backend.dto.response.MeasurementResponse;
import com.thang.gms_backend.entity.Customers;
import com.thang.gms_backend.entity.Measurements;
import com.thang.gms_backend.exception.AppException;
import com.thang.gms_backend.mapstruct.MeasurementMapper;
import com.thang.gms_backend.repository.CustomerRepository;
import com.thang.gms_backend.repository.MeasurementRepository;
import com.thang.gms_backend.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MeasurementServiceImpl implements MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final CustomerRepository customerRepository;
    private final MeasurementMapper measurementMapper;

    @Override
    public MeasurementResponse addMeasurements(String customerId, Measurements measurements) {
        Customers customers = customerRepository.findById(customerId).orElseThrow(
                () -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND)
        );
        if (customers != null) {
            measurements.setCustomer(customers);
            measurementRepository.save(measurements);
        }
        return measurementMapper.toMeasurementResponse(measurements);
    }

    @Override
    public MeasurementResponse updateMeasurements(String measurementId, String labelNew) {
        Measurements measurements = measurementRepository.findById(measurementId).orElseThrow(
                () -> new AppException(ErrorCode.MEASUREMENT_NOT_FOUND)
        );
        measurements.setLabel(labelNew);
        measurementRepository.save(measurements);
        return measurementMapper.toMeasurementResponse(measurements);
    }

    @Override
    public List<MeasurementResponse> getMeasurementsByCustomerId(String customerId) {
        Customers customer = customerRepository.findById(customerId).orElseThrow(
                () -> new AppException(ErrorCode.CUSTOMER_NOT_FOUND)
        );
        List<Measurements> measurementsList = measurementRepository.findByCustomerId(customer.getId());
        return measurementMapper.toMeasurementResponseList(measurementsList);
    }


}
