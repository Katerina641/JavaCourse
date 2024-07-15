package com.example.springboot.restapi.rest;

import com.example.springboot.restapi.dao.TeacherDAOInterface;
import com.example.springboot.restapi.entity.Teacher;
import com.example.springboot.restapi.service.TeacherServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeacherController {

    private TeacherServiceInterface teacherService;

    @Autowired
    public TeacherController(TeacherServiceInterface tService){
        teacherService = tService;
    }

    @GetMapping("/teachers")
    public List<Teacher> findAll(){

        return teacherService.findAll();
    }

    @GetMapping("/teachers/{teacherId}")
    public Teacher getTeacher(@PathVariable int teacherId){
        Teacher teacher = teacherService.findById(teacherId);
        if(teacher == null){
            throw new RuntimeException("Teacher id not found: " + teacherId);
        }
        return teacher;
    }

    @PostMapping("/teachers")
    public Teacher addTeacher(@RequestBody Teacher teacher){
        teacher.setId(0);
        Teacher teacherDB = teacherService.save(teacher);
        return teacherDB;
    }

    @PutMapping("/teachers")
    public Teacher updateTeacher(@RequestBody Teacher teacher){
        Teacher teacherDB = teacherService.save(teacher);
        return teacherDB;
    }

    @DeleteMapping("/teachers/{teacherId}")
    public String deleteTeacher(@PathVariable int teacherId){

        Teacher teacher = teacherService.findById(teacherId);

        if(teacher == null){
            throw new RuntimeException("Teacher id not found: " + teacherId);
        }

        teacherService.deleteById(teacherId);
        return "Deleted teacher id: " + teacherId;

    }





}
