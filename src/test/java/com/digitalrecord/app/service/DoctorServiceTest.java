package com.digitalrecord.app.service;

import com.digitalrecord.app.dto.DoctorProfileRequest;
import com.digitalrecord.app.entity.Doctor;
import com.digitalrecord.app.entity.User;
import com.digitalrecord.app.repository.DoctorRepository;
import com.digitalrecord.app.repository.UserRepository;
import com.digitalrecord.app.utility.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.print.Doc;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DoctorService doctorService;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
    }

    void testCreateDoctorProfile()
    {
        String email = "doctor@xyz.com";
        User user = User.builder().id("u001").email(email).build();
        DoctorProfileRequest request = DoctorProfileRequest.builder()
                .specialization("Cardiology")
                .experience("5 yrs")
                .qualification("MD")
                .build();
        Doctor doctor = Doctor.builder()
                .userId("u001")
                .specialization("Cardiology")
                .experience("5 yrs")
                .qualification("MD")
                .build();

        when(SecurityUtil.getCurrentUserEmail()).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.ofNullable((user)));
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);
        Doctor result = doctorService.createDoctorProfile(request);
        assertEquals("Cardiology",result.getSpecialization());
        verify(doctorRepository,times(1)).save(any(Doctor.class));
    }
}
