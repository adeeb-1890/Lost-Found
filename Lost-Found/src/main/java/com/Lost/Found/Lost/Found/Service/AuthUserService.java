package com.Lost.Found.Lost.Found.Service;

import com.Lost.Found.Lost.Found.Model.AppUser;
import com.Lost.Found.Lost.Found.Model.AuthUser;
import com.Lost.Found.Lost.Found.Repository.AppUserRepo;
import com.Lost.Found.Lost.Found.Repository.AuthUserRepo;
import com.Lost.Found.Lost.Found.Security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {

    private final AppUserRepo appUserRepo;
    private final AuthUserRepo authUserRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil; // Inject JwtUtil

    public AuthUserService(AppUserRepo appUserRepo,
                           AuthUserRepo authUserRepo,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil) {
        this.appUserRepo = appUserRepo;
        this.authUserRepo = authUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // ---------------- Signup ----------------
    public String signup(String username, String password, String name, String email, String department) {
        if (authUserRepo.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Save AppUser
        AppUser appUser = new AppUser();
        appUser.setName(name);
        appUser.setEmail(email);
        appUser.setDepartment(department);
        appUserRepo.save(appUser);

        // Save AuthUser
        AuthUser authUser = new AuthUser();
        authUser.setUsername(username);
        authUser.setPassword(passwordEncoder.encode(password));
        authUser.setAppUser(appUser);
        authUserRepo.save(authUser);

        return "User registered successfully!";
    }

    // ---------------- Login ----------------
    public String login(String username, String password) {
        try {
            // Authenticate credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // Fetch user to get role
            AuthUser authUser = authUserRepo.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Generate JWT
            return jwtUtil.generateToken(username, authUser.getRole());

        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid username or password");
        }
    }
}