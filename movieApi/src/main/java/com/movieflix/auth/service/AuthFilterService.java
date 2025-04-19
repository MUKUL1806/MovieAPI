package com.movieflix.auth.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class AuthFilterService extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    public AuthFilterService(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        String jwt;
        String username;

        System.out.println("🚨 Incoming Request URI: " + request.getRequestURI());
        System.out.println("🚨 Auth Header: " + authHeader);


        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            System.out.println("❌ Missing or malformed Authorization header");
            filterChain.doFilter(request,response);
            return;
        }

        //extract JWT
        jwt = authHeader.substring(7);
        try {
            username = jwtService.extractUsername(jwt);
        }catch(Exception e){
            System.out.println("❌ Failed to extract username: " + e.getMessage());
            filterChain.doFilter(request, response);
            return;
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails =userDetailsService.loadUserByUsername(username);
            System.out.println("✅ Loaded user: " + userDetails.getUsername());
            System.out.println("✅ User authorities: " + userDetails.getAuthorities());
            if(jwtService.isTokenValid(jwt,userDetails)){
                System.out.println("✅ Token is valid");

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                  userDetails,
                  null,
                  userDetails.getAuthorities()
                );

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                System.out.println("✅ Security context updated for user: " + username);
            }else{
                System.out.println("❌ Invalid token");
            }
        }

        filterChain.doFilter(request,response);

    }
}
