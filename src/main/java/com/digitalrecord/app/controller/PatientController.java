package com.digitalrecord.app.controller;

import com.digitalrecord.app.dto.PatientProfileRequest;
import com.digitalrecord.app.entity.Appointment;
import com.digitalrecord.app.entity.Patient;
import com.digitalrecord.app.entity.User;
import com.digitalrecord.app.repository.AppointmentRepository;
import com.digitalrecord.app.repository.PatientRepository;
import com.digitalrecord.app.repository.UserRepository;
import com.digitalrecord.app.service.PatientService;
import com.digitalrecord.app.utility.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    public PatientService patientService;

    @Autowired
    public AppointmentRepository appointmentRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PatientRepository patientRepository;

    @RequestMapping("/createProfile")
    public ResponseEntity<Patient> createPatientProfile(@RequestBody PatientProfileRequest request)
    {
        return ResponseEntity.ok(patientService.createPatientProfile(request));
    }

    @RequestMapping("/getProfile/{email}")
    public ResponseEntity<Patient> getPatientProfile(@PathVariable String email)
    {
        return ResponseEntity.ok(patientService.getPatientProfile(email));
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<Patient> updatePatientProfile(@PathVariable String email, @RequestBody PatientProfileRequest request)
    {
        Patient updatePatient = patientService.updatePatient(email,request);
        return ResponseEntity.ok(updatePatient);
    }
}
