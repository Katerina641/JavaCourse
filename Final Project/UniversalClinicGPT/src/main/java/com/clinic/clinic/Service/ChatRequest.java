package com.clinic.clinic.Service;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;


@Component
public class ChatRequest {

    private ChatGptRequestParam requestParam;

    private RequestBody requestBody;

    private Request request;

    public ChatRequest(ChatGptRequestParam requestParam) {
        this.requestParam = requestParam;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(String prompt) {

        this.setRequestBody(prompt);

        this.request = new Request.Builder()
                .url(this.requestParam.getRequestUrl())
                .post(this.requestBody)
                .addHeader("Authorization", "Bearer " + this.requestParam.getRequestKey())
                .addHeader("Content-Type", "application/json")
                .build();
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

    private void setRequestBody(String prompt) {
        MediaType mediaType = MediaType.parse("application/json");

        ChatGptMsgContent msgContent = new ChatGptMsgContent("text",prompt);

        ChatGptMsgContent[] tabContent = {msgContent};

        ChatGptMsgContentOwner chatOwner = new ChatGptMsgContentOwner("user",tabContent);

        ChatGptMsgContentOwner[] tabOwners = {chatOwner};

        ChatGptRequestMsg chatRequestMsg = new ChatGptRequestMsg(tabOwners,requestParam);

        this.requestBody = RequestBody.create(mediaType, new Gson().toJson(chatRequestMsg));

    }

}

