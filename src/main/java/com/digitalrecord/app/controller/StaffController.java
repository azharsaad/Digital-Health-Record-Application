package com.digitalrecord.app.controller;

import com.digitalrecord.app.dto.StaffDto;
import com.digitalrecord.app.dto.StaffProfileRequest;
import com.digitalrecord.app.entity.Staff;
import com.digitalrecord.app.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/api/staff")
@RestController
public class StaffController {

    @Autowired
    public StaffService service;

    @PostMapping("/profile")
    public ResponseEntity<Staff> createStaffProfile(@RequestBody StaffDto request)
    {
        return ResponseEntity.ok(service.createStaffProfile(request));
    }

    @GetMapping("/profile/{email}")
    public ResponseEntity<Staff> getCurrentStaffProfile(@PathVariable String email)
    {
        return ResponseEntity.ok(service.getCurrentStaffProfile(email));
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<Staff> updateStaffProfile(@PathVariable String email,@RequestBody StaffDto dto)
    {
        Staff staff = service.updateStaff(email,dto);
        return ResponseEntity.ok(staff);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllPatient()
    {
        service.deletePatients();
    }
}

