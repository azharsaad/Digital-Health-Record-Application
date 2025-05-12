package com.digitalrecord.app.service;

import com.digitalrecord.app.dto.AuthResponse;
import com.digitalrecord.app.dto.RegisterRequest;
import com.digitalrecord.app.entity.User;
import com.digitalrecord.app.repository.UserRepository;

import com.digitalrecord.app.utility.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    public AuthServiceTest()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser()
    {
        RegisterRequest request = new RegisterRequest();
        request.setName("Saad");
        request.setEmail("saad22@gmail.com");
        request.setPassword("UsxER1@jal2");
        request.setRole("ADMIN");

        when(passwordEncoder.encode(any(CharSequence.class))).thenReturn("encodedPassword");
        when(userRepository.save(any())).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(jwtUtil.generateToken(anyString())).thenReturn("fake-jwt-token");
        AuthResponse response = authService.register(request);
        assertEquals("fake-jwt-token",response.getToken());
        verify(userRepository,times(1)).save(any(User.class));
    }
}
