package com.thang.gms_backend.controller;

import com.thang.gms_backend.dto.request.LoginRequest;
import com.thang.gms_backend.dto.response.UserResponse;
import com.thang.gms_backend.entity.Users;
import com.thang.gms_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody Users request) {
        return ResponseEntity.ok(userService.saveUser(request));
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest request) {
        UserResponse response = userService.loginUser(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getProfile(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
