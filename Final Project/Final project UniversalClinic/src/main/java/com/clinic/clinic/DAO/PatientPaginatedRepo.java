package com.clinic.clinic.DAO;

import com.clinic.clinic.Entity.Patient;
import com.clinic.clinic.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PatientPaginatedRepo extends PagingAndSortingRepository<Patient, Long> {
    Page<Patient> findByUser(User user, Pageable pageable);

    List<Patient> findByLastNameAndUser(String lastName, User user);
}
