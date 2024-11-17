package com.code.taletrail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.code.taletrail.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
}
