package com.thang.gms_backend.service.impl;

import com.thang.gms_backend.constant.ErrorCode;
import com.thang.gms_backend.dto.request.LoginRequest;
import com.thang.gms_backend.dto.response.UserResponse;
import com.thang.gms_backend.entity.Users;
import com.thang.gms_backend.exception.AppException;
import com.thang.gms_backend.mapstruct.UserMapper;
import com.thang.gms_backend.repository.UserRepository;
import com.thang.gms_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {
    private  final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;
    @Override
    public UserResponse authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        if (authentication.isAuthenticated()) {
            Users user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

            return UserResponse.builder()
                    .token(jwtService.generateToken(request.getUsername()))
                    .id(user.getId())
                    .username(user.getUsername())
                    .fullname(user.getFullname())
                    .build();
        } else {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }
    }

    @Override
    public UserResponse rigister(Users user) {
        if(userRepository.existsByUsername(user.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        Users newUser = new Users();
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setUsername(user.getUsername());
        newUser.setFullname(user.getFullname());
        newUser = userRepository.save(newUser);
        return userMapper.toUserResponse(newUser);
    }
}
