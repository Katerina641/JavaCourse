package com.clinic.clinic.DAO;

import com.clinic.clinic.Entity.Doctor;
import com.clinic.clinic.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DoctorPaginatedRepo extends PagingAndSortingRepository<Doctor, Long> {
    Page<Doctor> findByUser(User user, Pageable pageable);
}