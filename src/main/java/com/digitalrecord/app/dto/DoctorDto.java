package com.digitalrecord.app.dto;

import com.digitalrecord.app.entity.Doctor;
import com.digitalrecord.app.entity.User;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {
    private Doctor doctor;
    private User user;
    private String specialization;
    private String availability;
    private String address;
    private String email;
    private String qualifications;
    private String experience;
}
