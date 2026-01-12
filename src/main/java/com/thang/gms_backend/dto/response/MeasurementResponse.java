package com.thang.gms_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class MeasurementResponse {
    private  String id;
    private String label; // Ví dụ: "Số đo tháng 10"
    private Double chest; // ngực
    private Double shoulder; // vai
    private Double waist; // eo
    private Double thigh;// đùi
    private Double arm; // bap tay
    private Double  hip; // mông
    private LocalDateTime createdAt = LocalDateTime.now();
}
