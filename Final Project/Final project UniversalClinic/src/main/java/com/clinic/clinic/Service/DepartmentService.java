package com.clinic.clinic.Service;

import com.clinic.clinic.Entity.Department;
import com.clinic.clinic.Entity.Patient;
import com.clinic.clinic.Entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();
    Department findById(Long id);
    Department save(Department department);
    void deleteById(Long id);

    Page<Department> getAllPaginated(int page, int i);

    List<Department> findByUser(User user);

    Page<Department> getDepartmentByUserPaginated(User user, int page, int size);
}
