package com.clinic.clinic.Service;

import com.clinic.clinic.Entity.Appointment;
import com.clinic.clinic.Entity.Department;
import com.clinic.clinic.Entity.Patient;
import com.clinic.clinic.Entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AppointmentService {
    List<Appointment> findAll();
    Appointment findById(Long id);
    Appointment save(Appointment appointment);
    void deleteById(Long id);

    Page<Appointment> getAllPaginated(int page, int i);

    List<Appointment> findByUser(User user);

    List<Appointment> findByDoctorId(Long doctorId);

    Page<Appointment> getAppointmentByUserPaginated(User user, int page, int size);
}
