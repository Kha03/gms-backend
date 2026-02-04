package com.thang.gms_backend.dto.request;

import com.thang.gms_backend.constant.OrderStatus;
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
    private OrderStatus status;
    private LocalDate receivedDate;
    @NotEmpty(message = "Measurement list cannot be empty")
    private List<String> measurementIds;
}
