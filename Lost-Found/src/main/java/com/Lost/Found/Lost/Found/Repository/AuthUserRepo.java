package com.Lost.Found.Lost.Found.Repository;

import com.Lost.Found.Lost.Found.Model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepo extends JpaRepository<AuthUser , Long> {
    Optional<AuthUser> findByUsername(String username);
}
