package com.clinic.clinic.Service;

public class ChatGptRequestMsg {

    private  String model;

    private ChatGptMsgContentOwner[] messages;

    private  float temperature;

    private  int max_tokens;

    private  int top_p;

    private  int frequency_penalty;

    private  int presence_penalty;


    public ChatGptRequestMsg(ChatGptMsgContentOwner[] messages, ChatGptRequestParam requestParam) {

        this.messages = messages;
        this.temperature = requestParam.getTemperature();
        this.max_tokens = requestParam.getMax_tokens();
        this.top_p = requestParam.getTop_p();
        this.frequency_penalty = requestParam.getFrequency_penalty();
        this.presence_penalty = requestParam.getPresence_penalty();
        this.model = requestParam.getModel();

    }

    public  String getModel() {
        return model;
    }

    public ChatGptMsgContentOwner[] getMessages() {
        return messages;
    }

    public  int getPresence_penalty() {
        return presence_penalty;
    }

    public  int getFrequency_penalty() {
        return frequency_penalty;
    }

    public  int getTop_p() {
        return top_p;
    }

    public  int getMax_tokens() {
        return max_tokens;
    }

    public  float getTemperature() {
        return temperature;
    }
}