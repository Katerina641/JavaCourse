package com.clinic.clinic.Controller;

import com.clinic.clinic.AuthUtils;
import com.clinic.clinic.Entity.Appointment;
import com.clinic.clinic.Entity.AppointmentDTO;
import com.clinic.clinic.Entity.User;
import com.clinic.clinic.Service.AppointmentService;
import com.clinic.clinic.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private final AppointmentService appointmentService;

    private final UserService userService;


    @Autowired
    private AuthUtils authUtils;

    public CalendarController(AppointmentService appointmentService, UserService userService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

    public List<AppointmentDTO> convertToDTO(List<Appointment> appointments) {
        return appointments.stream().map(appointment -> {
            AppointmentDTO dto = new AppointmentDTO(appointment.getId(), appointment.getAppointmentDate(), appointment.getTitle());
            return dto;
        }).collect(Collectors.toList());
    }

    @GetMapping
    public String index(Model model){
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }
        List<Appointment> appointments = appointmentService.findByUser(userAuthed);
        List<AppointmentDTO> appointmentDTOs = convertToDTO(appointments);

        model.addAttribute("username", userAuthed.getUsername());
        model.addAttribute("appointments", appointmentDTOs);
        model.addAttribute("title", "Calendar");
        return "calendar";
    }
}
