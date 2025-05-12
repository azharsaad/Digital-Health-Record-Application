package com.digitalrecord.app.service;

import com.digitalrecord.app.dto.PatientProfileRequest;
import com.digitalrecord.app.entity.Patient;
import com.digitalrecord.app.entity.User;
import com.digitalrecord.app.repository.PatientRepository;
import com.digitalrecord.app.repository.UserRepository;
import com.digitalrecord.app.utility.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PatientRepository patientRepository;

    public Patient createPatientProfile(PatientProfileRequest request)
    {
        String email = request.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        if(patientRepository.findByUserId(user.getId()).isPresent())
        {
            throw new RuntimeException("Patient profile already exists");
        }
        Patient patient = new Patient();
        patient.setUserId(user.getId());
        patient.setAge(request.getAge());
        patient.setGender(request.getGender());
        patient.setPhone(request.getPhone());
        patient.setBloodGroup(request.getBloodGroup());

        return patientRepository.save(patient);
    }

    public Patient getPatientProfile(String email)
    {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return patientRepository.findByUserId(user.getId()).orElseThrow(()-> new RuntimeException("Patient Profile not found"));
    }

    public Patient updatePatient(String email, PatientProfileRequest request) {
        Patient patient = patientRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
        patient.setAddress(request.getAddress());
        patient.setAge(request.getAge());
        patient.setPhone(request.getPhone());
        patient.setEmail(request.getEmail());
        patient.setGender(request.getGender());
        patient.setBloodGroup(request.getBloodGroup());
        patient.setDateOfBirth(request.getDateOfBirth());
        return patientRepository.save(patient);
    }
}
