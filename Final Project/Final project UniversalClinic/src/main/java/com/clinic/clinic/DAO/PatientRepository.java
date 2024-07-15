package com.clinic.clinic.DAO;

import com.clinic.clinic.Entity.Doctor;
import com.clinic.clinic.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT d FROM Patient d WHERE d.lastName LIKE %:lastName%")
    List<Patient> findByLastName(@Param("lastName") String lastName);
}
