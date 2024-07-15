package com.clinic.clinic.Controller;

import com.clinic.clinic.DAO.UserRepository;
import com.clinic.clinic.Entity.User;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/api/register")
    public String registerUser(@RequestBody JsonNode jsonNode) {
        System.out.println("Received registration data: " + jsonNode);

        JSONObject response = new JSONObject();
        JSONObject errors = new JSONObject();

        String username = jsonNode.has("userName") ? jsonNode.get("userName").asText() : null;
        String password = jsonNode.has("pass") ? jsonNode.get("pass").asText() : null;
        String repeatPass = jsonNode.has("repeatPass") ? jsonNode.get("repeatPass").asText() : null;

        System.out.println("Parsed data - Username: " + username + ", Password: " + password + ", RepeatPass: " + repeatPass);

        if (username == null || username.isEmpty()) {
            JSONObject usernameError = new JSONObject();
            usernameError.put("message", "Username is required");
            errors.put("username", usernameError);
        } else {
            User existUser = userRepository.findByUsername(username);
            if (existUser != null) {
                JSONObject usernameError = new JSONObject();
                usernameError.put("message", "Username already exists");
                errors.put("username", usernameError);
            }
        }

        if(username.length() < 5){
            JSONObject usernameError = new JSONObject();
            usernameError.put("message", "length min is 5 chars");
            errors.put("username", usernameError);
        }

        if (password == null || password.isEmpty()) {
            JSONObject passError = new JSONObject();
            passError.put("message", "Password is required");
            errors.put("password", passError);
        }

        if(password.length() < 8){
            JSONObject passError = new JSONObject();
            passError.put("message", "password min length is 8 chars");
            errors.put("password", passError);
        }

        if (repeatPass == null || repeatPass.isEmpty()) {
            JSONObject passRepeatError = new JSONObject();
            passRepeatError.put("message", "Repeat password is required");
            errors.put("repeatPass", passRepeatError);
        } else if (!repeatPass.equals(password)) {
            JSONObject passRepeatError = new JSONObject();
            passRepeatError.put("message", "Passwords do not match");
            errors.put("repeatPass", passRepeatError);
        }

        if (errors.length() > 0) {
            response.put("errors", errors);
            response.put("status", "failure");
        } else {
            String encodedPassword = passwordEncoder.encode(password);
            User user = new User();
            user.setUsername(username);
            user.setPassword(encodedPassword);
            userRepository.save(user);

            response.put("status", "success");
        }

        System.out.println("Response: " + response.toString());
        return response.toString();
    }

}
