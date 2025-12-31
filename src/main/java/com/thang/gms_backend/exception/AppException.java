package com.thang.gms_backend.exception;

import com.thang.gms_backend.constant.ErrorCode;
import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private  final ErrorCode errorCode;
    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
