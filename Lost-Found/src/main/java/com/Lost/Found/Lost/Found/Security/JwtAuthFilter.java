package com.Lost.Found.Lost.Found.Security;

import com.Lost.Found.Lost.Found.Model.Role;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws
            ServletException, IOException {
            final String authHeader = request.getHeader("Authorization");

            String username = null;
            String jwtToken = null;

            if(authHeader != null && authHeader.startsWith("Bearer ")){
                jwtToken = authHeader.substring(7);
                try{
                    username = jwtUtil.getUsername(jwtToken);
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }

            if(username != null && SecurityContextHolder.getContext()
                    .getAuthentication() == null){
                if(jwtUtil.validateToken(jwtToken , username)){
                    Role role = jwtUtil.getRole(jwtToken);
                    UsernamePasswordAuthenticationToken authToken = new
                            UsernamePasswordAuthenticationToken(
                                    username , null , Collections.singletonList(
                                            new SimpleGrantedAuthority("ROLE_"  + role.name())
                    ));
                    authToken.setDetails(new WebAuthenticationDetailsSource()
                            .buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request , response);
    }
}
