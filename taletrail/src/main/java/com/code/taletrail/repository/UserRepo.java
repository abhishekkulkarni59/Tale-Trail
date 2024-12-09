package com.code.taletrail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.code.taletrail.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
