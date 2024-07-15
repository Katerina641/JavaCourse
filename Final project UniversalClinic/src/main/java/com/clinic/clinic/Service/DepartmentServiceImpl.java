package com.clinic.clinic.Service;

import com.clinic.clinic.DAO.AppointmentPaginatedRepo;
import com.clinic.clinic.DAO.AppointmentRepository;
import com.clinic.clinic.DAO.DepartmentPaginatedRepo;
import com.clinic.clinic.DAO.DepartmentRepository;
import com.clinic.clinic.Entity.Department;
import com.clinic.clinic.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;

    private final DepartmentPaginatedRepo departmentPaginatedRepo;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentPaginatedRepo departmentPaginatedRepo) {
        this.departmentRepository = departmentRepository;
        this.departmentPaginatedRepo = departmentPaginatedRepo;
    }


    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Long id) {
        Optional<Department> result = departmentRepository.findById(id);
        return result.orElse(null);

    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);

    }

    @Override
    public Page<Department> getAllPaginated(int page, int size) {
        return departmentPaginatedRepo.findAll(PageRequest.of(page,size));
    }

    @Override
    public List<Department> findByUser(User user) {
        return departmentRepository.findByName(String.valueOf(user));
    }

    @Override
    public Page<Department> getDepartmentByUserPaginated(User user, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return departmentPaginatedRepo.findByUser(user, pageable);
    }
}
