package com.thang.gms_backend.service;

import com.thang.gms_backend.dto.request.LoginRequest;
import com.thang.gms_backend.dto.response.UserResponse;
import com.thang.gms_backend.entity.Users;

public interface UserService {

    UserResponse getUserById(String id);

    UserResponse saveUser(Users user);
    UserResponse loginUser(LoginRequest request);
}
