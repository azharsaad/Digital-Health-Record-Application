package com.digitalrecord.app.repository;

import com.digitalrecord.app.entity.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor,String> {

    Optional<Doctor> findBySpecialization (String specialization);
    Optional<Doctor> findByEmail (String email);
    Optional<Doctor> findByUserId(String id);
}
