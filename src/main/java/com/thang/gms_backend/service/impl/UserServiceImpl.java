package com.thang.gms_backend.service.impl;

import com.thang.gms_backend.constant.ErrorCode;
import com.thang.gms_backend.dto.response.UserResponse;
import com.thang.gms_backend.entity.Users;
import com.thang.gms_backend.exception.AppException;
import com.thang.gms_backend.repository.UserRepository;
import com.thang.gms_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    @Override
    public UserResponse loginUser(com.thang.gms_backend.dto.request.LoginRequest request) {
        Users user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }
        return mapToUserResponse(user);
    }
    @Override
    public UserResponse getUserById(String userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return mapToUserResponse(user);
    }

    @Override
    public UserResponse saveUser(Users user) {
        if(userRepository.existsByUsername(user.getUsername())){
           throw new AppException(ErrorCode.USER_EXISTED);
        }
        Users newUser = new Users();
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setUsername(user.getUsername());
        newUser.setFullname(user.getFullname());
        newUser = userRepository.save(newUser);
        return mapToUserResponse(newUser);

    }// Helper method để chuyển đổi Entity sang DTO
    private UserResponse mapToUserResponse(Users user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setFullName(user.getFullname());
        return response;
    }
}
