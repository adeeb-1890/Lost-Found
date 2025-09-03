package com.Lost.Found.Lost.Found.Repository;

import com.Lost.Found.Lost.Found.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser , Long> {
    Optional<AppUser> findByEmail(String email);
}
