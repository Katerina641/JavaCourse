package com.clinic.clinic.Service;

public class ChatGptMsgContentOwner {
    private String role;
    private ChatGptMsgContent[] content;

    public ChatGptMsgContentOwner(String role, ChatGptMsgContent[] content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }

    public ChatGptMsgContent[] getContent() {
        return content;
    }
}