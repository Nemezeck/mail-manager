package com.bd.edu.co.mail_manager.Service;

import com.bd.edu.co.mail_manager.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

}
