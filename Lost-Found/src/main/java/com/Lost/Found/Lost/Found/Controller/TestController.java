package com.Lost.Found.Lost.Found.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        // Get authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Username
        String username = authentication.getName();

        // Role(s)
        String roles = authentication.getAuthorities().toString();

        return "Hello, " + username + "! Your role(s): " + roles;
    }
}
