package com.bd.edu.co.mail_manager.DTO;

public class AddContactDTO {
    String contactUserId;
    String selfUsername;

    public String getContactUserId() {
        return contactUserId;
    }

    public void setContactUserId(String contactUserId) {
        this.contactUserId = contactUserId;
    }

    public String getSelfUsername() {
        return selfUsername;
    }

    public void setSelfUsername(String selfUsername) {
        this.selfUsername = selfUsername;
    }
}
