package com.clinic.clinic.DAO;

import com.clinic.clinic.Entity.Appointment;
import com.clinic.clinic.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}