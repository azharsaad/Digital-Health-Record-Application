package com.digitalrecord.app.service;

import com.digitalrecord.app.dto.DoctorProfileRequest;
import com.digitalrecord.app.entity.Doctor;
import com.digitalrecord.app.entity.User;
import com.digitalrecord.app.repository.DoctorRepository;
import com.digitalrecord.app.repository.UserRepository;
import com.digitalrecord.app.utility.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public DoctorRepository doctorRepository;

    public Doctor createDoctorProfile (DoctorProfileRequest request)
    {
        String email = request.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Optional<Doctor> existingDoctor = doctorRepository.findByUserId(user.getId());
        if(existingDoctor.isPresent())
        {
            throw new RuntimeException("Doctor profile already exists");
        }
        Doctor doctor = new Doctor();
        doctor.setName(request.getName());
        doctor.setExperience(request.getExperience());
        doctor.setAvailability(request.getAvailability());
        doctor.setQualification(request.getQualification());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setAddress(request.getAddress());
        doctor.setUserId(user.getId());
        doctor.setEmail(user.getEmail());
        return doctorRepository.save(doctor);
    }

    public Doctor getDoctorProfile(String doctorEmail) {
        Doctor email = doctorRepository.findByEmail(doctorEmail).orElseThrow(()->new RuntimeException("Doctor not found"));
        User user = userRepository.findByEmail(doctorEmail).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return doctorRepository.findByUserId(user.getId()).orElseThrow(()-> new RuntimeException("Doctor profile not found"));
    }

    public Doctor updateDoctorProfile(String email, DoctorProfileRequest request) {
        Doctor doctor = doctorRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Doctor with email " + email + " is not found"));
        doctor.setExperience(request.getExperience());
        doctor.setName(request.getName());
        doctor.setAvailability(request.getAvailability());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setAddress(request.getAddress());
        doctor.setQualification(request.getQualification());
        return doctorRepository.save(doctor);
    }
}
