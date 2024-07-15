package com.clinic.clinic.DAO;

import com.clinic.clinic.Entity.Department;
import com.clinic.clinic.Entity.Patient;
import com.clinic.clinic.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT d FROM Department d WHERE d.departmentName LIKE %:departmentName%")
    List<Department> findByName(@Param("departmentName") String departmentName);
}
