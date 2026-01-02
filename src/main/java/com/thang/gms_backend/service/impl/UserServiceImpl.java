package com.thang.gms_backend.service.impl;

import com.thang.gms_backend.constant.ErrorCode;
import com.thang.gms_backend.dto.request.LoginRequest;
import com.thang.gms_backend.dto.response.UserResponse;
import com.thang.gms_backend.entity.Users;
import com.thang.gms_backend.exception.AppException;
import com.thang.gms_backend.mapstruct.UserMapper;
import com.thang.gms_backend.repository.UserRepository;
import com.thang.gms_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private  final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse loginUser(LoginRequest request) {
      return null;
    }
    @Override
    public UserResponse getUserById(String userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }
    // Method to load user details by username (email)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database by email (username)
        Optional<Users> userInfo = userRepository.findByUsername(username);

        if (userInfo.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        // Convert UserInfo to UserDetails (UserInfoDetails)
        Users user = userInfo.get();
        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }
    @Override
    public UserResponse saveUser(Users user) {

        return null;

    }
}
