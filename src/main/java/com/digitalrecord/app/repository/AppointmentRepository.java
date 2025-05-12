package com.digitalrecord.app.repository;

import com.digitalrecord.app.entity.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends MongoRepository<Appointment,String> {

    List<Appointment> findByDoctorId (String doctorId);
    List<Appointment> findByPatientId (String patientId);

    boolean existsByPatientIdAndDoctorIdAndAppointmentTime(String patientId, String doctorId, LocalDateTime appointmentTime);
}
