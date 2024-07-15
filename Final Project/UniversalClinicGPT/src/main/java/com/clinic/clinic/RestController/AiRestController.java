package com.clinic.clinic.RestController;


import okhttp3.*;
import com.clinic.clinic.Service.ChatRequest;
import com.clinic.clinic.Service.ChatResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(produces = "application/json")
public class AiRestController {

    private ChatRequest chatGptRequest;
    private ChatResponse chatGptResponse;

    private OkHttpClient httpClient = new OkHttpClient();

    AiRestController(ChatRequest chatRequest, ChatResponse chatResponse) {

        this.chatGptRequest = chatRequest;
        this.chatGptResponse = chatResponse;
    }

    @GetMapping(value = "/AI-Assistant")
    public String chat(@RequestParam String prompt) {

        String responseStr;

        this.chatGptRequest.setRequest(prompt);

        try {
            responseStr = httpClient.newCall(this.chatGptRequest.getRequest()).execute().body().string();
            this.chatGptResponse.setResponseTab(responseStr);
            System.out.println("HERE IS THE RESPONSE " + responseStr);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        return this.chatGptResponse.responseMessage();
    }

}