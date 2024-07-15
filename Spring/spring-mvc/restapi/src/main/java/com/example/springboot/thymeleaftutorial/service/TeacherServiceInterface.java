package com.example.springboot.thymeleaftutorial.service;

import com.example.springboot.thymeleaftutorial.entity.Teacher;

import java.util.List;

public interface TeacherServiceInterface {

    List<Teacher> findAll();
    Teacher findById(int id);
    Teacher save(Teacher teacher);
    void deleteById(int id);

}
