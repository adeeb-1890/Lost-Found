package com.Lost.Found.Lost.Found.Controller;

import com.Lost.Found.Lost.Found.DTO.LoginRequest;
import com.Lost.Found.Lost.Found.DTO.SignupRequest;
import com.Lost.Found.Lost.Found.Service.AuthUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    private final AuthUserService authUserService;

    public AuthUserController(AuthUserService authUserService){
        this.authUserService = authUserService;
    }

    // Signup endpoint
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        return authUserService.signup(
                request.getUsername(),
                request.getPassword(),
                request.getName(),
                request.getEmail(),
                request.getDepartment()
        );
    }

    // login
    @PostMapping ("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        return authUserService.login(loginRequest.getUsername() , loginRequest.getPassword());
    }
}
