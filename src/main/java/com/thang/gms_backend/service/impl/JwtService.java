package com.thang.gms_backend.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.KeyStore;
import java.util.Base64;

@Service
public class JwtService {
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;
//    private Key getSigningKey() {
//        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
//        return Key
//    }
}
