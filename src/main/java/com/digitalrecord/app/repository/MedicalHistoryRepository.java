package com.digitalrecord.app.repository;

import com.digitalrecord.app.entity.MedicalHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRepository extends MongoRepository<MedicalHistory,String> {

    List<MedicalHistory> findByPatientId(String patientId);

    List<MedicalHistory> findByDoctorId(String doctorId);

}
