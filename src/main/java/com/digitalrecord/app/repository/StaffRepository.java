package com.digitalrecord.app.repository;

import com.digitalrecord.app.entity.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends MongoRepository<Staff, String> {
    Optional<Staff> findByUserId(String userId);

    Optional<Staff> findByEmail(String email);

    //  Staff findByPhone(String phone);
}
