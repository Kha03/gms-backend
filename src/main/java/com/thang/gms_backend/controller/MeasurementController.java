package com.thang.gms_backend.controller;

import com.thang.gms_backend.dto.response.MeasurementResponse;
import com.thang.gms_backend.entity.Measurements;
import com.thang.gms_backend.service.MeasurementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/measurements")
@RequiredArgsConstructor
@Tag(name = "Measurement Management", description = "Quản lý số đo khách hàng.")
@SecurityRequirement(name = "bearerAuth")
public class MeasurementController {
    private final MeasurementService measurementService;
    @Operation(summary = "Add Measurements for Customer")

    @PostMapping("/{customerId}")
    public ResponseEntity<MeasurementResponse> addMeasurements(@PathVariable String customerId,
                                                               @Valid @RequestBody Measurements measurementRequest) {
        return ResponseEntity.ok(measurementService.addMeasurements(customerId, measurementRequest));
    }

    @PostMapping("/update/{measurementId}")
    public ResponseEntity<MeasurementResponse> updateMeasurements(@PathVariable String measurementId,
                                                                  @RequestParam String labelNew) {
        return ResponseEntity.ok(measurementService.updateMeasurements(measurementId, labelNew));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<MeasurementResponse>> getMeasurementsByCustomerId(@PathVariable String customerId) {
        return ResponseEntity.ok(measurementService.getMeasurementsByCustomerId(customerId));
    }

}
