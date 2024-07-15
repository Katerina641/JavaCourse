package com.clinic.clinic.Controller;


import com.clinic.clinic.AuthUtils;
import com.clinic.clinic.Entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import okhttp3.*;
import com.clinic.clinic.Service.ChatRequest;
import com.clinic.clinic.Service.ChatResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Controller
@RequestMapping("/ai-helper")
public class AiHelperController {

    @Autowired
    private AuthUtils authUtils;

    @GetMapping
    public String getAllDoctorsByUser(Model model) {
        User userAuthed = authUtils.getLoggedInUser();
        model.addAttribute("username", userAuthed.getUsername());
        model.addAttribute("title", "Ai helper");
        return "ai-helper";
    }



}
