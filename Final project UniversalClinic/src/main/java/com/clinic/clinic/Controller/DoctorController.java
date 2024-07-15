package com.clinic.clinic.Controller;

import com.clinic.clinic.AuthUtils;
import com.clinic.clinic.Entity.Appointment;
import com.clinic.clinic.Entity.Doctor;
import com.clinic.clinic.Entity.Patient;
import com.clinic.clinic.Entity.User;
import com.clinic.clinic.Service.AppointmentService;
import com.clinic.clinic.Service.DoctorService;
import com.clinic.clinic.Service.UserService;
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

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;
    private final UserService userService;

    @Autowired
    private AuthUtils authUtils;


    private final AppointmentService appointmentService;
    @Autowired
    public DoctorController(DoctorService doctorService, UserService userService, AppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    @InitBinder //for editing
    public void initBinder(WebDataBinder binder){
        StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, trimmer);
    }

    @GetMapping
    public String index(Model model){
        return "redirect:/doctors/all";
    }

    @GetMapping("/add")
    public String showAddDoctorForm(Model model) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }

        model.addAttribute("userId", userAuthed.getId());
        model.addAttribute("doctor", new Doctor());
        model.addAttribute("title", "Add Doctor");
        return "add-doctor-form";
    }

    @PostMapping("/add")
    public String addDoctor(@Valid @ModelAttribute Doctor doctor, BindingResult bindingResult) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }
        if(bindingResult.hasErrors()){
            return  "add-doctor-form";
        }
        doctorService.save(doctor);
        userAuthed.addDoctor(doctor);
        userService.save(userAuthed);
        return "redirect:/doctors";


    }

    @GetMapping("/all")
    public String getAllDoctorsByUser(Model model, @RequestParam(defaultValue = "0") int page) {

        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }

        Page<Doctor> doctorPage = doctorService.getDoctorsByUserPaginated(userAuthed, page, 5);

        model.addAttribute("username", userAuthed.getUsername());
        model.addAttribute("doctorPage", doctorPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("title", "Doctors");
        return "doctors";
    }

    @GetMapping("/{doctorId}/update")
    public String showUpdateDoctorForm(@PathVariable Long doctorId, Model model) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }

        Doctor doctor = doctorService.findById(doctorId);
        if (doctor == null) {
            return "redirect:/error"; // Redirect to an error page or another appropriate action
        }
        model.addAttribute("doctor", doctor);
        model.addAttribute("title", "Update Doctor");
        return "update-doctor-form"; // Name of the Thymeleaf template
    }

    @PutMapping("/{doctorId}/update")
    public String updateDoctor(@PathVariable Long doctorId, @ModelAttribute Doctor updatedDoctor) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }

        Doctor doctor = doctorService.findById(doctorId);
        if (doctor == null) {
            return "redirect:/error";
        }
        // Update doctor details
        doctor.setFirstName(updatedDoctor.getFirstName());
        doctor.setLastName(updatedDoctor.getLastName());
        doctor.setEmail(updatedDoctor.getEmail());
        doctor.setPhone(updatedDoctor.getPhone());
        doctor.setDepartment(updatedDoctor.getDepartment());
        // Save the updated doctor back to the database
        doctorService.save(doctor);
        // Return the updated doctor
        return "redirect:/doctors";
    }

    @DeleteMapping("/{doctorId}/delete")
    public String deleteDoctor(@PathVariable Long doctorId) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }

        Doctor doctor = doctorService.findById(doctorId);
        if (doctor == null) {
            return "redirect:/error";
        }

        userAuthed.removeDoctor(doctor);  // Remove the doctor from the user's list
        doctorService.deleteById(doctorId);  // Delete the doctor from the database
        userService.save(userAuthed);  // Update the user in the database
        return "redirect:/doctors";
    }



    //to do !!!
    @GetMapping("/by-lastname/{lastName}")
    public ResponseEntity<List<Doctor>> getDoctorsByLastName(@PathVariable String lastName) {
        User userAuthed = authUtils.getLoggedInUser();

        List<Doctor> doctors = doctorService.findByLastNameAndUser(lastName, userAuthed);
        if (doctors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(doctors);
    }
}
