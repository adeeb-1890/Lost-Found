package com.Lost.Found.Lost.Found.Service;

import com.Lost.Found.Lost.Found.Model.AuthUser;
import com.Lost.Found.Lost.Found.Repository.AuthUserRepo;
import com.Lost.Found.Lost.Found.Security.AuthUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final AuthUserRepo authUserRepo;

    @Autowired
    public AuthUserDetailsService(AuthUserRepo authUserRepo) {
        this.authUserRepo = authUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database
        AuthUser authUser = authUserRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found, please sign in"));

        // Return AuthUserPrincipal directly (handles roles/authorities)
        return new AuthUserPrincipal(authUser);
    }
}
