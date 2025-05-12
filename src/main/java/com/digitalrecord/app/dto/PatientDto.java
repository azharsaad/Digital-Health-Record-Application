package com.digitalrecord.app.dto;

import com.digitalrecord.app.entity.Patient;
import com.digitalrecord.app.entity.User;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

    private Patient patient;
    private User user;
    private String gender;
    private String age;
    private String address;
}
