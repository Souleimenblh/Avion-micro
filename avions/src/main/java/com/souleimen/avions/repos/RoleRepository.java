package com.souleimen.avions.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.souleimen.avions.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
Role findByRole(String role);
}
