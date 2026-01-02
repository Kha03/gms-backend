package com.thang.gms_backend.constant;

import lombok.Getter;

@Getter

public enum ErrorCode {
    AUTHENTICATION_FAILED(1001, "Người dùng chưa xác thực"),
    INVALID_KEY(4000, "Mã lỗi không hợp lệ"),

    USER_EXISTED(4001, "Người dùng đã tồn tại"),
    INVALID_PASSWORD(4011, "Mật khẩu không chính xác"),
    INVALID_CREDENTIALS(4012, "Thông tin đăng nhập không hợp lệ"),
    USER_NOT_FOUND(4002, "Người dùng không tồn tại"),
    CUSTOMER_NOT_FOUND(4003, "Không tìm thấy khách hàng"),
    PHONE_ALREADY_EXISTS(4004, "Số điện thoại đã được đăng ký bởi khách khác"),
    UNCATEGORIZED_EXCEPTION(5000, "Lỗi chưa được phân loại");
    private int code;
    private String message;ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
