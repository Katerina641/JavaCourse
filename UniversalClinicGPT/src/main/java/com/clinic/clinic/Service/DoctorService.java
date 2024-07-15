package com.clinic.clinic.Service;

import com.clinic.clinic.Entity.Doctor;
import com.clinic.clinic.Entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DoctorService {
    List<Doctor> findAll();
    Doctor findById(Long id);
    Doctor save(Doctor doctor);
    void deleteById(Long id);

    Page<Doctor> getAllPaginated(int page, int i);

    List<Doctor> findByLastName(String lastName);

    Page<Doctor> getDoctorsByUserPaginated(User user, int page, int size);

    List<Doctor> findByLastNameAndUser(String lastName, User userAuthed);
}
