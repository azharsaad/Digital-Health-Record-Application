package com.digitalrecord.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientProfileRequest {
    private String age;
    private String gender;
    private String phone;
    private String bloodGroup;
    private LocalDate dateOfBirth;
    private String address;
    private String email;
    private String userId;
}
