package com.thang.gms_backend.repository;

import com.thang.gms_backend.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Boolean existsByUsername(String username);

    Optional<Users> findByUsername(String username);
}
