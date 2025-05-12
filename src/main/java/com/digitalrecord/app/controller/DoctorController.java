package com.digitalrecord.app.controller;

import com.digitalrecord.app.dto.DoctorProfileRequest;
import com.digitalrecord.app.entity.Appointment;
import com.digitalrecord.app.entity.Doctor;
import com.digitalrecord.app.entity.User;
import com.digitalrecord.app.repository.AppointmentRepository;
import com.digitalrecord.app.repository.DoctorRepository;
import com.digitalrecord.app.repository.UserRepository;
import com.digitalrecord.app.service.DoctorService;
import com.digitalrecord.app.utility.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    public DoctorService doctorService;
    
    @Autowired
    public UserRepository userRepository;
    
    @Autowired
    public DoctorRepository doctorRepository;
    
    @Autowired
    public AppointmentRepository appointmentRepository;

    @PostMapping("/createProfile")
    public ResponseEntity<Doctor> createDoctorProfile(@RequestBody DoctorProfileRequest request)
    {
        return ResponseEntity.ok(doctorService.createDoctorProfile(request));
    }

    @GetMapping("/getProfile/{email}")
    public ResponseEntity<Doctor> getDoctorProfile(@PathVariable String email)
    {
        return ResponseEntity.ok(doctorService.getDoctorProfile(email));
    }
    @PutMapping("/updateProfile/{email}")
    public ResponseEntity<Doctor> updateDoctorProfile(@PathVariable String email, @RequestBody DoctorProfileRequest request)
    {
        Doctor updateDoctor = doctorService.updateDoctorProfile(email,request);
        return ResponseEntity.ok(updateDoctor);
    }
    
    @GetMapping("/appointments/{email}")
    public ResponseEntity<List<Appointment>> getDoctorAppointments(@PathVariable String email)
    {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("user not found"));
        Doctor doctor = doctorRepository.findByUserId(user.getId()).orElseThrow(()-> new RuntimeException("Doctor not found"));
        return ResponseEntity.ok(appointmentRepository.findByDoctorId(doctor.getId()));
    }

    @GetMapping("/getAllProfile")
    public ResponseEntity<List<Doctor>> getAllDoctorProfile()
    {
        List<Doctor> doctors = doctorRepository.findAll();
        return ResponseEntity.ok(doctors.stream().toList());
    }
}
