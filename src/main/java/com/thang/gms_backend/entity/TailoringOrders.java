package com.thang.gms_backend.entity;

import com.thang.gms_backend.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class TailoringOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String note;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDate receivedDate; // Ngày hẹn lấy

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @ManyToOne
    @JoinColumn(name = "measurement_id")
    private Measurements measurement;
}
