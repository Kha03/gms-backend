package com.thang.gms_backend.controller;

import com.thang.gms_backend.constant.ErrorCode;
import com.thang.gms_backend.dto.request.LoginRequest;
import com.thang.gms_backend.dto.response.UserResponse;
import com.thang.gms_backend.entity.Users;
import com.thang.gms_backend.exception.AppException;
import com.thang.gms_backend.service.AuthService;
import com.thang.gms_backend.service.UserService;
import com.thang.gms_backend.service.impl.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "đăng ký, đăng nhập.")
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private  final JwtService jwtService;
    @Operation(summary = "Register User")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody Users request) {
        return ResponseEntity.ok(authService.rigister(request));
    }

    @Operation(summary = "Login User")

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest request) {
        UserResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get User Profile")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getProfile(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @Operation(summary = "Refresh Token")
    @PostMapping("/refresh")
    public ResponseEntity<UserResponse> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        // 1. Kiểm tra tính hợp lệ của Token (Chữ ký, hết hạn)
        String username = jwtService.extractUsername(refreshToken);

        // 2. Kiểm tra xem có đúng là loại Refresh Token không
        if (username != null && jwtService.isRefreshToken(refreshToken)) {
            // 3. Tạo Access Token mới
            String newAccessToken = jwtService.generateToken(username);
            String newRefreshToken = jwtService.generateRefreshToken(username); // Tạo mới Refresh Token (Rotation)
            return ResponseEntity.ok(UserResponse.builder()
                    .token(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .username(username)
                    .build());
        }

        throw new AppException(ErrorCode.AUTHENTICATION_FAILED);
    }
}
