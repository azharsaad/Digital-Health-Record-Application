package com.digitalrecord.app.service;

import com.digitalrecord.app.dto.PrescriptionDto;
import com.digitalrecord.app.entity.*;
import com.digitalrecord.app.repository.*;
import com.digitalrecord.app.utility.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrescriptionService {

    @Autowired
    public PrescriptionRepository prescriptionRepository;

    @Autowired
    public AppointmentRepository appointmentRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public DoctorRepository doctorRepository;

    @Autowired
    public PatientRepository patientRepository;

    public PrescriptionDto createPrescription(PrescriptionDto prescriptionDto)
    {
        Appointment appointment = appointmentRepository.findById(prescriptionDto.getAppointmentId())
                .orElseThrow(()-> new RuntimeException("Appointment not found"));
        Prescription prescription = new Prescription();
        prescription.setAppointmentId(appointment.getId());
        prescription.setDiagnosis(appointment.getReason());
        prescription.setDate(LocalDate.ofEpochDay(System.currentTimeMillis()));
        prescription.setDoctorId(appointment.getDoctorId());
        prescription.setPatientId(appointment.getPatientId());
        prescription.setMedicines(prescriptionDto.getMedicines());
        prescription.setDescription(appointment.getReason());
        Prescription saved = prescriptionRepository.save(prescription);
        return mapToDto(saved);
    }

    public PrescriptionDto getPrescriptionById(String id)
    {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Prescription not found"));
        return mapToDto(prescription);
    }

    public List<PrescriptionDto> getPrescriptionForCurrentDoctor()
    {
        String email = SecurityUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
        Doctor doctor = doctorRepository.findByUserId(user.getId())
                .orElseThrow(()-> new RuntimeException("Doctor not found"));
        List<Prescription> prescriptions = prescriptionRepository.findByDoctorId(doctor.getId());
        return prescriptions.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public List<PrescriptionDto> getPrescriptionForCurrentPatient()
    {
        String email = SecurityUtil.getCurrentUserEmail();
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("user not found"));
        Patient patient = patientRepository.findByUserId(user.getId())
                .orElseThrow(()-> new RuntimeException("Patient not found"));
        List<Prescription> prescriptions = prescriptionRepository.findByPatientId(patient.getId());
        return prescriptions.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private PrescriptionDto mapToDto(Prescription prescription)
    {
        PrescriptionDto dto = new PrescriptionDto();
        dto.setId(prescription.getId());
        dto.setAppointmentId(prescription.getAppointmentId());
        dto.setDoctorId(prescription.getDoctorId());
        dto.setPatientId(prescription.getPatientId());
        dto.setMedicines(prescription.getMedicines());
        dto.setDiagnosis(prescription.getDiagnosis());
        return dto;
    }


}
