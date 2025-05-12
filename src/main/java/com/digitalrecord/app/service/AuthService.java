package com.digitalrecord.app.service;

import com.digitalrecord.app.dto.AuthResponse;
import com.digitalrecord.app.dto.LoginRequest;
import com.digitalrecord.app.dto.RegisterRequest;
import com.digitalrecord.app.entity.User;
import com.digitalrecord.app.repository.UserRepository;
import com.digitalrecord.app.utility.CustomUserDetailsService;
import com.digitalrecord.app.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.digitalrecord.app.enums.Role.*;

@Service
public class AuthService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public CustomUserDetailsService userDetailsService;

    public AuthResponse register(RegisterRequest request)
    {
        //check for existing email
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        } else {//creating or saving user
            User user = new User();
            user.setEmail(request.getEmail());
            user.setName(request.getName());
            user.setRole(valueOf(request.getRole().toUpperCase()));
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);
            //generating jwt
            String token = jwtUtil.generateToken(user.getEmail());
            return new AuthResponse(token, user.getRole());
        }
    }

    public AuthResponse login(LoginRequest request)
    {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        }
        catch (BadCredentialsException exception)
        {
            throw new RuntimeException("Invalid email or password");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(userDetails.getUsername());
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new UsernameNotFoundException("user not found"));
        return AuthResponse.builder().token(token)
                .role(user.getRole()).build();
    }
}
