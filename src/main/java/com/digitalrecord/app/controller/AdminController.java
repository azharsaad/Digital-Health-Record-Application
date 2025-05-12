package com.digitalrecord.app.controller;

import com.digitalrecord.app.entity.Appointment;
import com.digitalrecord.app.entity.Prescription;
import com.digitalrecord.app.entity.User;
import com.digitalrecord.app.enums.Role;
import com.digitalrecord.app.repository.AppointmentRepository;
import com.digitalrecord.app.repository.PrescriptionRepository;
import com.digitalrecord.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public AppointmentRepository appointmentRepository;

    @Autowired
    public PrescriptionRepository prescriptionRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers()
    {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/users/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role)
    {
        Role userRole = Role.valueOf(role.toUpperCase());
        return ResponseEntity.ok(userRepository.findByRole(userRole));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id)
    {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/user/{id}/role")
    public ResponseEntity<User> updateUserRole(@PathVariable String id, @RequestParam Role role)
    {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        user.setRole(role);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointments()
    {
        return ResponseEntity.ok(appointmentRepository.findAll());
    }

    @GetMapping("/prescriptions")
    public ResponseEntity<List<Prescription>> getAllPrescriptions()
    {
        return ResponseEntity.ok(prescriptionRepository.findAll());
    }
}
