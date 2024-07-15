package com.clinic.clinic.Controller;

import com.clinic.clinic.AuthUtils;
import com.clinic.clinic.Entity.*;
import com.clinic.clinic.Service.AppointmentService;
import com.clinic.clinic.Service.DoctorService;
import com.clinic.clinic.Service.PatientService;
import com.clinic.clinic.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    private final PatientService patientService;
    private final UserService userService;

    @Autowired
    private AuthUtils authUtils;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService, PatientService patientService, UserService userService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.userService = userService;
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public String index(Model model){
        return "redirect:/appointments/all";
    }

    @GetMapping("/add")
    public String showAddDoctorForm(Model model) {
        User userAuthed = authUtils.getLoggedInUser();
        model.addAttribute("username", userAuthed.getUsername());
        model.addAttribute("userId", '1');
        model.addAttribute("title", "Add Appointment");
        return "add-appointment-form";
    }


    @PostMapping("/add")
    public String addAppointment(@RequestParam String title, @RequestParam Long doctorId, @RequestParam Long patientId, @RequestParam LocalDateTime datetime) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }
        Doctor doctor = doctorService.findById(doctorId);
        Patient patient = patientService.findById(patientId);

        Appointment appointment = new Appointment();
        appointment.setUser(userAuthed);
        appointment.setTitle(title);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(datetime);

        appointmentService.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/all")
    public String getAllAppointmentsByUser(Model model, @RequestParam(defaultValue = "0") int page) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }

        Page<Appointment> appointmentsPage = appointmentService.getAppointmentByUserPaginated(userAuthed, page, 5);
        model.addAttribute("appointmentsPage", appointmentsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("username", userAuthed.getUsername());
        model.addAttribute("title", "Appointments");
        return "appointments";
    }

    @GetMapping("/{appointmentId}/update")
    public String showUpdateDoctorForm(@PathVariable Long appointmentId, Model model) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }
        Appointment appointment = appointmentService.findById(appointmentId);
        if (appointment == null) {
            return "redirect:/error";
        }
        model.addAttribute("appointment", appointment);
        model.addAttribute("username", userAuthed.getUsername());
        model.addAttribute("title", "Update Appointment");
        return "update-appointment-form"; // Name of the Thymeleaf template
    }

    @GetMapping("/{appointmentId}")
    public String getAppointment(Model model, @PathVariable Long appointmentId) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }
        Appointment appointment = appointmentService.findById(appointmentId);
        if (appointment == null) {
            return "redirect:/error";
        }
        model.addAttribute("username", userAuthed.getUsername());
        model.addAttribute("appointment", appointment);
        return "appointment";
    }

    @PutMapping("/{appointmentId}/update")
    public String updateAppointment(@RequestParam String title, @PathVariable Long appointmentId, @RequestParam Long doctorId, @RequestParam Long patientId, @RequestParam LocalDateTime datetime) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }
        Appointment appointment = appointmentService.findById(appointmentId);
        if (appointment == null) {
            return "redirect:/error";
        }
        Doctor doctor = doctorService.findById(doctorId);
        Patient patient = patientService.findById(patientId);

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(datetime);
        appointment.setTitle(title);

        appointmentService.save(appointment);
        return "redirect:/appointments/all";
    }

    @DeleteMapping("/{appointmentId}/delete")
    public String deleteAppointment(@PathVariable Long appointmentId) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }
        Appointment appointment = appointmentService.findById(appointmentId);
        if (appointment == null) {
            return "redirect:/error";
        }
        userAuthed.removeAppointment(appointment);  // Remove the  from the user's list
        appointmentService.deleteById(appointmentId);  // Delete the  from the database
        userService.save(userAuthed);  // Update the user in the database
        return "redirect:/appointments";
    }
}
