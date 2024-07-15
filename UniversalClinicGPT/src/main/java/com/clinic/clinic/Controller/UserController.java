package com.clinic.clinic.Controller;

import com.clinic.clinic.Entity.Doctor;
import com.clinic.clinic.Entity.User;
import com.clinic.clinic.Service.DoctorService;
import com.clinic.clinic.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final DoctorService doctorService;

    // Constructor-based injection is recommended
    public UserController(UserService userService, DoctorService doctorService) {
        this.userService = userService;
        this.doctorService = doctorService;
    }

//    @GetMapping("/new")
//    public ResponseEntity<User> createUser() {
//        User newUser = new User("Name", "0000");
//        userService.save(newUser);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
//    }
}
