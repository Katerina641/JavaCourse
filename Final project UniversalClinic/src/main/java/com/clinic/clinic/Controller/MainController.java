package com.clinic.clinic.Controller;

import com.clinic.clinic.AuthUtils;
import com.clinic.clinic.DAO.AppointmentRepository;
import com.clinic.clinic.DAO.DoctorRepository;
import com.clinic.clinic.DAO.PatientRepository;
import com.clinic.clinic.DAO.UserRepository;
import com.clinic.clinic.Entity.Appointment;
import com.clinic.clinic.Entity.Doctor;
import com.clinic.clinic.Entity.Patient;
import com.clinic.clinic.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserRepository userRepository;

    private final AppointmentRepository appointmentRepository;

    private final DoctorRepository doctorRepository;

    private final PatientRepository patientRepository;

    @Autowired
    private AuthUtils authUtils;

    public MainController(UserRepository userRepository, AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public String index(Model model){
        User userAuthed = authUtils.getLoggedInUser();
        List<Appointment> appointments = appointmentRepository.findAll();
        List<Doctor> doctors = doctorRepository.findAll();
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients.size());
        model.addAttribute("doctors", doctors.size());
        model.addAttribute("appointments", appointments.size());
        model.addAttribute("username", userAuthed.getUsername());
        model.addAttribute("title", "Dashboard");
        return "index";
    }
}
