package com.example.springboot.thymeleaftutorial.controller;

import com.example.springboot.thymeleaftutorial.entity.Teacher;
import com.example.springboot.thymeleaftutorial.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private TeacherService teacherService;

    public TeacherController(TeacherService myTeacherService){
        teacherService = myTeacherService;
    }

    @GetMapping("/get")
    public String getTeachers(Model model){

        // get teachers from the database
        List<Teacher> teachers = teacherService.findAll();

        //add teachers to the spring model
        model.addAttribute("teachers", teachers);

        return "teachers/get-teachers";
    }

    @GetMapping("/addTeacher")
    public String addTeacher(Model model){
        Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        return "teachers/teacher-form";
    }
    @GetMapping("/updateTeacher")
    public String updateTeacher(@RequestParam("teacherId") int id, Model model){
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        return "teachers/teacher-form";
    }
    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher){
        teacherService.save(teacher);
        return "redirect:/teachers/get";
    }
    @GetMapping("/deleteTeacher")
    public String deleteTeacher(@RequestParam("teacherId") int id){
        teacherService.deleteById(id);
        return "redirect:/teachers/get";
    }
}



