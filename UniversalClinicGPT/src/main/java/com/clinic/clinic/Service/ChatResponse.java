package com.clinic.clinic.Service;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class ChatResponse {

    private CompletionTab responseTab;

    public ChatResponse() {
    }

    public CompletionTab getResponseTab() {
        return responseTab;
    }

    public void setResponseTab(String responseBody) {
        Gson gson = new Gson();
        this.responseTab  = gson.fromJson(responseBody, CompletionTab.class);
    }

    public String responseMessage(){

        String responseStr = "\n";
        if(this.responseTab.choices != null ) {
            for (CompletionFormat completion : this.responseTab.choices) {
                responseStr += completion.message.content + "\n";
            }
        }
        return responseStr;
    }

    private static class CompletionTab {
        private CompletionFormat[] choices;
    }

    private static class CompletionFormat {
        private Message message;
        private int index;
        private Object logprobs;
        private String finish_reason;
    }

    private static class Message {
        private String role ;
        private String content;
    }



}
