package com.digitalrecord.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedicalHistoryRequest {
    private String patientId;
    private String diagnosis;
    private List<String> symptoms;
    private List<String> medications;
    private String notes;
}
