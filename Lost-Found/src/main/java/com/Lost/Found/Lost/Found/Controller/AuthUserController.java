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

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        // Returns JWT instead of plain success string
        return authUserService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }
}
