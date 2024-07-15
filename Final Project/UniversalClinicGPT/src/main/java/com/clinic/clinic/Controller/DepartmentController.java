package com.clinic.clinic.Controller;

import com.clinic.clinic.AuthUtils;
import com.clinic.clinic.Entity.*;
import com.clinic.clinic.Service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DoctorService doctorService;
    private final UserService userService;

    @Autowired
    private AuthUtils authUtils;

    @Autowired
    public DepartmentController(DepartmentService departmentService, DoctorService doctorService, UserService userService) {
        this.departmentService = departmentService;
        this.doctorService = doctorService;
        this.userService = userService;
    }

    @InitBinder //for editing
    public void initBinder(WebDataBinder binder){
        StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, trimmer);
    }

    @GetMapping
    public String index(Model model){
        return "redirect:/departments/all";
    }


    @GetMapping("/add")
    public String getAddDepartmentForm(Model model) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }
        Department department = new Department();
        model.addAttribute("department", department);
        model.addAttribute("username", userAuthed.getUsername());
        model.addAttribute("title", "Add Department");
        return "add-department-form";
    }

    @PostMapping("/add")
    public String addDepartment(@Valid @ModelAttribute Department department, BindingResult bindingResult) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }
        if(bindingResult.hasErrors()){
            return  "add-department-form";
        }
        department.setUser(userAuthed);
        departmentService.save(department);
        userAuthed.addDepartment(department);
        userService.save(userAuthed);
        return "redirect:/departments";
    }

    @GetMapping("/all")
    public String getAllDepartments(Model model, @RequestParam(defaultValue = "0") int page) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }
        Page<Department> departmentsPage = departmentService.getDepartmentByUserPaginated(userAuthed, page, 5);
        model.addAttribute("departmentsPage", departmentsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("username", userAuthed.getUsername());
        model.addAttribute("title", "Departments");
        return "departments";
    }

    @GetMapping("/{departmentId}/update")
    public String showUpdateDepartmentForm(@PathVariable Long departmentId, Model model) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }
        Department department = departmentService.findById(departmentId);
        if (department == null) {
            return "redirect:/error";
        }

        model.addAttribute("username", userAuthed.getUsername());
        model.addAttribute("department", department);
        model.addAttribute("title", "Update Department");
        return "update-department-form"; // Name of the Thymeleaf template
    }

    @GetMapping("/{departmentId}")
    public String getAppointment(Model model, @PathVariable Long departmentId) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }
        Department department = departmentService.findById(departmentId);
        if (department == null) {
            return "redirect:/error";
        }
        model.addAttribute("username", userAuthed.getUsername());
        model.addAttribute("department", department);
        return "department";
    }

    @PutMapping("/{departmentId}/update")
    public String updateDepartment(@PathVariable Long departmentId, @ModelAttribute Department updatedDepartment) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }

        Department department = departmentService.findById(departmentId);
        if (department == null) {
            return "redirect:/error";
        }
        department.setDepartmentName(updatedDepartment.getDepartmentName());
        departmentService.save(department);
        return "redirect:/departments/all";
    }

    @DeleteMapping("/{departmentId}/delete")
    public String deleteDepartment(@PathVariable Long departmentId) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }

        Department department = departmentService.findById(departmentId);
        if (department == null) {
            return "redirect:/error";
        }

        userAuthed.removeDepartment(department);  // Remove the doctor from the user's list
        departmentService.deleteById(departmentId);  // Delete the doctor from the database
        userService.save(userAuthed);  // Update the user in the database

        return "redirect:/departments";
    }
}
