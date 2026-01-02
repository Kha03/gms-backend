package com.thang.gms_backend.service;

import com.thang.gms_backend.dto.request.LoginRequest;
import com.thang.gms_backend.dto.response.UserResponse;
import com.thang.gms_backend.entity.Users;

public interface AuthService {
    UserResponse authenticate(LoginRequest request);

    UserResponse rigister(Users request);
}
