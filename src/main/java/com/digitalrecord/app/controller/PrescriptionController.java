package com.digitalrecord.app.controller;

import com.digitalrecord.app.dto.PrescriptionDto;
import com.digitalrecord.app.repository.UserRepository;
import com.digitalrecord.app.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    public PrescriptionService prescriptionService;

    @Autowired
    public UserRepository userRepository;

    @PostMapping
    public ResponseEntity<PrescriptionDto> createPrescription (@RequestBody PrescriptionDto prescriptionDto)
    {
        return ResponseEntity.ok(prescriptionService.createPrescription(prescriptionDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDto> getPrescriptionById(@PathVariable String id)
    {
        return ResponseEntity.ok(prescriptionService.getPrescriptionById(id));
    }

    @GetMapping("/doctor")
    public ResponseEntity<List<PrescriptionDto>> getPrescriptionsByDoctor()
    {
        return ResponseEntity.ok(prescriptionService.getPrescriptionForCurrentDoctor());
    }

    @GetMapping("/patient")
    public ResponseEntity<List<PrescriptionDto>> getPrescriptionsByPatient()
    {
        return ResponseEntity.ok(prescriptionService.getPrescriptionForCurrentPatient());
    }
}
