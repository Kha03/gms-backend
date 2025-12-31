package com.thang.gms_backend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_KEY(4000, "Mã lỗi không hợp lệ"),

    USER_EXISTED(4001, "Người dùng đã tồn tại"),
    INVALID_PASSWORD(4011, "Mật khẩu không chính xác"),
    USER_NOT_FOUND(4002, "Người dùng không tồn tại"),
    CUSTOMER_NOT_FOUND(4003, "Không tìm thấy khách hàng"),
    PHONE_ALREADY_EXISTS(4004, "Số điện thoại đã được đăng ký bởi khách khác"),
    UNCATEGORIZED_EXCEPTION(5000, "Lỗi chưa được phân loại");
    private int code;
    private String message;
}
