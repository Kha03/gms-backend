package com.thang.gms_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  String id;
    @Column(nullable = false, unique = true)
    @Size(min = 4, message = "Tên đăng nhập phải ít nhất 4 ký tự")
    private String username;
    @Column(nullable = false)
    @Size(min = 8, message = "Mật khẩu phải ít nhất 8 ký tự")
    private String password;
    @Column(nullable = false)
    private String fullname;
}
