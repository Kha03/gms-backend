package com.thang.gms_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Measurements {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  String id;

    private String label; // Ví dụ: "Số đo tháng 10"

    private Double chest; // ngực
    private Double shoulder; // vai
    private Double waist; // eo
    private Double thigh;// đùi
    private Double arm; // bap tay
    private Double  hip; // mông

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;

}
