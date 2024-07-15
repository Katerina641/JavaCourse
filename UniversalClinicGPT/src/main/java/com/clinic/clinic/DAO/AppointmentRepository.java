package com.clinic.clinic.DAO;

import com.clinic.clinic.Entity.Appointment;
import com.clinic.clinic.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUser(User user);

    List<Appointment> findByDoctorId(Long doctorId);
}
