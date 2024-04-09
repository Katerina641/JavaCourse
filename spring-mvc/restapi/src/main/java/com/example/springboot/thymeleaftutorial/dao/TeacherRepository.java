package com.example.springboot.thymeleaftutorial.dao;

import com.example.springboot.thymeleaftutorial.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    public List<Teacher> findAllByOrderByLastNameAsc();

}

