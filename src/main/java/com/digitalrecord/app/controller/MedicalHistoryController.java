package com.digitalrecord.app.controller;

import com.digitalrecord.app.dto.MedicalHistoryDto;
import com.digitalrecord.app.dto.MedicalHistoryRequest;
import com.digitalrecord.app.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-history")
public class MedicalHistoryController {

    @Autowired
    public MedicalHistoryService medicalHistoryService;

    @PostMapping
    public ResponseEntity<MedicalHistoryDto> createRecord(@RequestBody MedicalHistoryRequest request)
    {
        return ResponseEntity.ok(medicalHistoryService.createRecord(request));
    }

    @GetMapping("/patient")
    public ResponseEntity<List<MedicalHistoryDto>> getPatientHistory()
    {
        return ResponseEntity.ok(medicalHistoryService.getHistoryByPatient());
    }

    @GetMapping("/doctor")
    public ResponseEntity<List<MedicalHistoryDto>> getDoctorHistory()
    {
        return ResponseEntity.ok(medicalHistoryService.getHistoryByDoctor());
    }

}
