package com.clinic.clinic.Controller;

import com.clinic.clinic.AuthUtils;
import com.clinic.clinic.Entity.Doctor;
import com.clinic.clinic.Entity.Patient;
import com.clinic.clinic.Entity.User;
import com.clinic.clinic.Service.PatientService;
import com.clinic.clinic.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;
    private final UserService userService;

    @Autowired
    private AuthUtils authUtils;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

        StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, trimmer);
    }


    @Autowired
    public PatientController(PatientService patientService, UserService userService) {
        this.patientService = patientService;
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model){
        return "redirect:/patients/all";
    }

    @GetMapping("/add")
    public String showAddDoctorForm(Model model) {
        User userAuthed = authUtils.getLoggedInUser();
        model.addAttribute("userId", '1');
        model.addAttribute("patient", new Patient());
        model.addAttribute("title", "Add Patient");
        model.addAttribute("username", userAuthed.getUsername());
        return "add-patient-form";
    }

    @PostMapping("/add")
    public String addPatient(@Valid @ModelAttribute Patient patient, BindingResult bindingResult) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }
        if(bindingResult.hasErrors()){
            return  "add-patient-form";
        }
        patientService.save(patient);
        userAuthed.addPatient(patient);
        userService.save(userAuthed);
        return "redirect:/patients/all";
    }


    @GetMapping("/all")
    public String getAllPatientsByUser(Model model, @RequestParam(defaultValue = "0") int page) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }

        Page<Patient> patientsPage = patientService.getPatientByUserPaginated(userAuthed, page, 5);
        model.addAttribute("patientsPage", patientsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("username", userAuthed.getUsername());
        model.addAttribute("title", "Patients");
        return "patients";
    }

    @GetMapping("/{patientId}/update")
    public String showUpdatePatientForm(@PathVariable Long patientId, Model model) {
        User userAuthed = authUtils.getLoggedInUser();
        Patient patient = patientService.findById(patientId);
        if (patient == null) {
            return "redirect:/error"; // Redirect to an error page or another appropriate action
        }
        model.addAttribute("username", userAuthed.getUsername());
        model.addAttribute("patient", patient);
        model.addAttribute("title", "Update Patient");
        return "update-patient-form"; // Name of the Thymeleaf template
    }

    @PutMapping("/{patientId}/update")
    public String updatePatient(@PathVariable Long patientId, @ModelAttribute Patient updatedPatient) {
        Patient patient = patientService.findById(patientId);
        if (patient == null) {
            return "redirect:/error";
        }

        patient.setFirstName(updatedPatient.getFirstName());
        patient.setLastName(updatedPatient.getLastName());
        patient.setEmail(updatedPatient.getEmail());
        patient.setPhone(updatedPatient.getPhone());
        patient.setBirthday(updatedPatient.getBirthday());
        patientService.save(patient);
        return "redirect:/patients/all";
    }

    @DeleteMapping("/{patientId}/delete")
    public String deletePatient(@PathVariable Long patientId) {
        User userAuthed = authUtils.getLoggedInUser();
        if (userAuthed == null) {
            return "redirect:/login";
        }
        Patient patient = patientService.findById(patientId);
        if (patient == null) {
            return "redirect:/error";
        }
        userAuthed.removePatient(patient);  // Remove the doctor from the user's list
        patientService.deleteById(patientId);  // Delete the doctor from the database
        userService.save(userAuthed);  // Update the user in the database
        return "redirect:/patients";
    }


    @GetMapping("/by-lastname/{lastName}")
    public ResponseEntity<List<Patient>> getDoctorsByLastName(@PathVariable String lastName) {
        User userAuthed = authUtils.getLoggedInUser();

        List<Patient> patients = patientService.findByLastNameAndUser(lastName, userAuthed);
        if (patients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(patients);
    }
}
