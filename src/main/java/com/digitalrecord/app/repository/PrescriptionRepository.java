package com.digitalrecord.app.repository;

import com.digitalrecord.app.entity.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository extends MongoRepository<Prescription,String> {

    List<Prescription> findByDoctorId (String doctorId);
    List<Prescription> findByPatientId (String patientId);
    Optional<Prescription> findByAppointmentId (String appointmentId);
}
