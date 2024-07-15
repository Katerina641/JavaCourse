package com.clinic.clinic.DAO;

import com.clinic.clinic.Entity.Department;
import com.clinic.clinic.Entity.Doctor;
import com.clinic.clinic.Entity.Patient;
import com.clinic.clinic.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentPaginatedRepo extends PagingAndSortingRepository<Department, Long> {

    Page<Department> findByUser(User user, Pageable pageable);
}
