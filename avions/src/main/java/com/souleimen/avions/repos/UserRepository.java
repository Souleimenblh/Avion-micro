package com.souleimen.avions.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.souleimen.avions.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
User findByUsername (String username);
}