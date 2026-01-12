package com.thang.gms_backend.dto.response;

import com.thang.gms_backend.constant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String id;
    private String note;
    private OrderStatus status ;
    private LocalDateTime createdDate;
    private LocalDate receivedDate; // Ngày hẹn lấy
    private CustomerResponse customer; // Thông tin khách hàng
    private List<MeasurementResponse> measurements; // DANH SÁCH SỐ ĐO CHI TIẾT
}
