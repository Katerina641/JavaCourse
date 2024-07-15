package com.clinic.clinic.Service;

import com.clinic.clinic.DAO.DoctorPaginatedRepo;
import com.clinic.clinic.DAO.DoctorRepository;
import com.clinic.clinic.Entity.Appointment;
import com.clinic.clinic.Entity.Doctor;
import com.clinic.clinic.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorPaginatedRepo doctorPaginatedRepo;

    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorPaginatedRepo doctorPaginatedRepo) {
        this.doctorRepository = doctorRepository;
        this.doctorPaginatedRepo = doctorPaginatedRepo;
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findById(Long id) {
        Optional<Doctor> result = doctorRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public Page<Doctor> getAllPaginated(int page, int size) {
        return doctorPaginatedRepo.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Doctor> findByLastName(String lastName) {
        return doctorRepository.findByLastName(lastName);
    }

    @Override
    public Page<Doctor> getDoctorsByUserPaginated(User user, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return doctorPaginatedRepo.findByUser(user, pageable);
    }

    public List<Doctor> findByLastNameAndUser(String lastName, User user) {
        return doctorRepository.findByLastNameAndUser(lastName, user);
    }
}
