package com.thang.gms_backend.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderRequest {
    @NotBlank(message = "Customer ID is required")
    private String customerId;
    private String note;
    @Future(message = "Ngày nhận phải sau ngày hiện tại")
    private LocalDate receivedDate;
    @NotEmpty(message = "Measurement list cannot be empty")
    private List<String> measurementIds;
}
