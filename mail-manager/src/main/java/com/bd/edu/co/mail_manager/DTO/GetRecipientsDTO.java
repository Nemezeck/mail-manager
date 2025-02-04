package com.bd.edu.co.mail_manager.DTO;

import java.util.List;

public class GetRecipientsDTO {
    private List<Integer> recipientsIDS;

    public List<Integer> getRecipientsIDS() {
        return recipientsIDS;
    }

    public void setRecipientsIDS(List<Integer> recipientsIDS) {
        this.recipientsIDS = recipientsIDS;
    }
}
