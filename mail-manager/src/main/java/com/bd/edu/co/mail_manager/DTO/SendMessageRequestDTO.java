package com.bd.edu.co.mail_manager.DTO;

import java.util.List;

public class SendMessageRequestDTO {
    private String messageId;
    private String username;
    private List<String> mailContactos;
    private String idType;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public List<String> getMailContactos() {
        return mailContactos;
    }

    public void setMailContactos(List<String> mailContactos) {
        this.mailContactos = mailContactos;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
