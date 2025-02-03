package com.bd.edu.co.mail_manager.DTO;

import java.util.Map;

public class UpdateMessageDTO {
    private String messageId;
    private String username;
    private Map<String, Object> updatedField;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, Object> getUpdatedField() {
        return updatedField;
    }

    public void setUpdatedField(Map<String, Object> updatedField) {
        this.updatedField = updatedField;
    }
}
