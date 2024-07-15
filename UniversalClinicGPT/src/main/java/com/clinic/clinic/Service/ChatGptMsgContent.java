package com.clinic.clinic.Service;

public class ChatGptMsgContent {
    private String type;
    private String text;

    public ChatGptMsgContent(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
