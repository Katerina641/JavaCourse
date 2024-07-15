package com.clinic.clinic.DAO;

import com.clinic.clinic.Entity.Appointment;
import com.clinic.clinic.Entity.Doctor;
import com.clinic.clinic.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AppointmentPaginatedRepo extends PagingAndSortingRepository<Appointment, Long> {
    Page<Appointment> findByUser(User user, Pageable pageable);
}
