package com.digitalrecord.app.service;

import com.digitalrecord.app.dto.MedicalHistoryDto;
import com.digitalrecord.app.dto.MedicalHistoryRequest;
import com.digitalrecord.app.entity.*;
import com.digitalrecord.app.repository.*;
import com.digitalrecord.app.utility.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicalHistoryService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistoryDto createRecord(MedicalHistoryRequest request)
    {
        String email = SecurityUtil.getCurrentUserEmail();
        User doctor = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("doctor not found"));
        MedicalHistory history = MedicalHistory.builder()
                .doctorId(doctor.getId())
                .patientId(request.getPatientId())
                .diagnosis(request.getDiagnosis())
                .visitedDate(LocalDate.now())
                .symptoms(request.getSymptoms())
                .medications(request.getMedications())
                .notes(request.getNotes())
                .build();
        return mapToDto(medicalHistoryRepository.save(history));
    }

    public List<MedicalHistoryDto> getHistoryByPatient()
    {
        String email = SecurityUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("user not found"));
        List<MedicalHistory> records = medicalHistoryRepository.findByPatientId(user.getId());
        return records.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<MedicalHistoryDto> getHistoryByDoctor()
    {
        String email = SecurityUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("user not found"));
        List<MedicalHistory> records = medicalHistoryRepository.findByDoctorId(user.getId());
        return records.stream().map(this::mapToDto).collect(Collectors.toList());
    }


    private MedicalHistoryDto mapToDto(MedicalHistory medicalHistory) {
        MedicalHistoryDto dto = new MedicalHistoryDto();
        dto.setId(medicalHistory.getId());
        dto.setDiagnosis(medicalHistory.getDiagnosis());
        dto.setVisitDate(medicalHistory.getVisitedDate());
        dto.setMedications(medicalHistory.getMedications());
        dto.setSymptoms(medicalHistory.getSymptoms());
        dto.setNotes(medicalHistory.getNotes());
        return dto;
    }
}
