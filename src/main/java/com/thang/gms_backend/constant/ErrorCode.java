package com.thang.gms_backend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_KEY(400, "Mã lỗi không hợp lệ"),
    USER_EXISTED(409, "Người dùng đã tồn tại"),
    CUSTOMER_NOT_FOUND(404, "Không tìm thấy khách hàng"),
    PHONE_ALREADY_EXISTS(409, "Số điện thoại đã được đăng ký bởi khách khác"),
    UNCATEGORIZED_EXCEPTION(500, "Lỗi chưa được phân loại");
    private int code;
    private String message;
}
