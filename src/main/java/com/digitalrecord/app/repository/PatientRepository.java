package com.digitalrecord.app.repository;

import com.digitalrecord.app.entity.Patient;
import com.digitalrecord.app.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
@Repository
public interface PatientRepository extends MongoRepository<Patient,String> {

    Optional<Patient> findByUserId (String userId);
    Optional<Patient> findByPhone (String phone);
    Optional<Patient> findByEmail(String email);
}
