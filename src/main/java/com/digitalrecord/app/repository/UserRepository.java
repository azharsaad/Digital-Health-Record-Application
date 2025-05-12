package com.digitalrecord.app.repository;

import com.digitalrecord.app.entity.User;
import com.digitalrecord.app.enums.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByEmail (String email);
    Optional<User> findByName (String name);
    List<User> findByRole(Role role);
}
