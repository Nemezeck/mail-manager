package com.bd.edu.co.mail_manager.DTO;

import com.bd.edu.co.mail_manager.Entity.ArchivoAdjunto;

import java.util.List;

public class ForwardMessageRequestDTO {
    String messageId;
    String senderUsername;
    List<String> mailContactos;
    String idType;
    String username;
    String newBody;
    List<AttachmentDTO> newAttachments;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
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

    public String getNewBody() {
        return newBody;
    }

    public void setNewBody(String newBody) {
        this.newBody = newBody;
    }

    public List<AttachmentDTO> getNewAttachments() {
        return newAttachments;
    }

    public void setNewAttachments(List<AttachmentDTO> newAttachments) {
        this.newAttachments = newAttachments;
    }
}
