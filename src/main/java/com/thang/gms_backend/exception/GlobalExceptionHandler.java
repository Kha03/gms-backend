package com.thang.gms_backend.exception;

import com.thang.gms_backend.constant.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // duùng để bắt tất cả các exception kế thừa từ AppException
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handlingAppException(AppException ex) {
        ErrorCode errorCode = ex.getErrorCode();


        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ex.getErrorCode().getCode())
                .message(ex.getErrorCode().getMessage())
                .timestamp(java.time.LocalDateTime.now())
                .build();
        return ResponseEntity.ok(errorResponse);
    }
    // 2. Bắt lỗi Validation (@Valid trên Controller)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlingValidation(MethodArgumentNotValidException exception) {
        Map<String, String> details = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                details.put(error.getField(), error.getDefaultMessage())
        );
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(400)
                .message("Dữ liệu đầu vào không hợp lệ")
                .details(details)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }
    // 3. Bắt các lỗi hệ thống khác (Ví dụ: DB sập, NullPointer...)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlingRuntimeException(Exception exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
//                .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage())
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.internalServerError().body(errorResponse);
    }
    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handlingBadCredentialsException(BadCredentialsException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ErrorCode.INVALID_CREDENTIALS.getCode()) // Unauthorized
                .message(ErrorCode.INVALID_CREDENTIALS.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
    @ExceptionHandler({
            io.jsonwebtoken.security.SignatureException.class,
            io.jsonwebtoken.ExpiredJwtException.class,
            io.jsonwebtoken.MalformedJwtException.class,
            io.jsonwebtoken.security.KeyException.class
    })
    public ResponseEntity<ErrorResponse> handleJwtExceptions(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ErrorCode.AUTHENTICATION_FAILED.getCode())
                .message(ErrorCode.AUTHENTICATION_FAILED.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}
