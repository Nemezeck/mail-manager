package com.bd.edu.co.mail_manager.Controller;

import com.bd.edu.co.mail_manager.DTO.*;
import com.bd.edu.co.mail_manager.Entity.ArchivoAdjunto;
import com.bd.edu.co.mail_manager.Entity.Mensaje;
import com.bd.edu.co.mail_manager.Repository.MessageRepository;
import com.bd.edu.co.mail_manager.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api/message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/create")
    public ResponseEntity<Mensaje> create(@RequestBody MensajeRequestDTO requestDTO) {
        Mensaje m = messageService.createMensaje(requestDTO);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @GetMapping("/inbox")
    public ResponseEntity<Map<?, ?>> getAllMailsByType(
            @RequestParam(name = "idType") String idType,
            @RequestParam(name = "username") String username) {
        Map<?, ?> m = messageService.getAllMessagesByType(idType, username);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @PostMapping("/addAttachment")
    public ResponseEntity<ArchivoAdjunto> addAttachment(@RequestBody AttachmentDTO attachmentDTO){
        ArchivoAdjunto a= messageService.addAttachments(attachmentDTO.getMessageId(), attachmentDTO.getUsername(),
                attachmentDTO.getAttachmentId(), attachmentDTO.getAttachmentName());
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @DeleteMapping("/removeAttachment")
    public ResponseEntity<String> removeAttachment(@RequestBody AttachmentDTO attachmentDTO){
        String s = messageService.removeAttachment(attachmentDTO.getMessageId(), attachmentDTO.getUsername(),
                attachmentDTO.getAttachmentId(), attachmentDTO.getAttachmentName());
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<Map<String, List<String>>> sendMessage(@RequestBody SendMessageRequestDTO sendMessageRequestDTO){
        Map<String, List<String>> m = messageService.sendMessage(sendMessageRequestDTO.getMessageId(), sendMessageRequestDTO.getUsername(),
                sendMessageRequestDTO.getMailContactos(), sendMessageRequestDTO.getIdType());
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateMessage(@RequestBody UpdateMessageDTO updateMessageDTO){
        String s = messageService.updateMensaje(updateMessageDTO.getMessageId(), updateMessageDTO.getUsername(), updateMessageDTO.getUpdatedField());
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PostMapping("/forward")
    public ResponseEntity<Map<String, List<String>>> forwardMessage(@RequestBody ForwardMessageRequestDTO dto){
        Map<String, List<String>> m = messageService.forwardMessage(dto.getMessageId(), dto.getSenderUsername(), dto.getMailContactos(),
                dto.getIdType(), dto.getUsername(), dto.getNewBody());
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Map<?, ?>> getMessage(@RequestParam String messageId, @RequestParam String username, @RequestParam String idType){
        Map<?,?> m = messageService.getMessageInfo(idType, username, messageId);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

}
