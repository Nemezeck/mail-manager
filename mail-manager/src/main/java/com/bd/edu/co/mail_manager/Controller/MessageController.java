package com.bd.edu.co.mail_manager.Controller;

import com.bd.edu.co.mail_manager.DTO.AttachmentDTO;
import com.bd.edu.co.mail_manager.DTO.MensajeRequestDTO;
import com.bd.edu.co.mail_manager.DTO.SendMessageRequestDTO;
import com.bd.edu.co.mail_manager.Entity.ArchivoAdjunto;
import com.bd.edu.co.mail_manager.Entity.Destinatario;
import com.bd.edu.co.mail_manager.Entity.Mensaje;
import com.bd.edu.co.mail_manager.Repository.TipoCarpetaRepository;
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
    private TipoCarpetaRepository repository;

    @PostMapping("/create")
    public ResponseEntity<Mensaje> register(@RequestBody MensajeRequestDTO requestDTO) {
        Mensaje m = messageService.createMensaje(requestDTO);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @GetMapping("/inbox")
    public ResponseEntity<List<Mensaje>> getAllMailsByType(
            @RequestParam(name = "idType") String idType,
            @RequestParam(name = "username") String username) {
        List<Mensaje> m = messageService.getAllMessagesByType(idType, username);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }


    @PostMapping("/addAttachment")
    public ResponseEntity<ArchivoAdjunto> addAttachment(@RequestBody AttachmentDTO attachmentDTO){
        ArchivoAdjunto a= messageService.addAttachments(attachmentDTO.getMessageId(), attachmentDTO.getUsername(),
                attachmentDTO.getAttachmentId(), attachmentDTO.getAttachmentName());
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<Map<String, List<String>>> sendMessage(@RequestBody SendMessageRequestDTO sendMessageRequestDTO){
        Map<String, List<String>> m = messageService.sendMessage(sendMessageRequestDTO.getMessageId(), sendMessageRequestDTO.getUsername(),
                sendMessageRequestDTO.getMailContactos(), sendMessageRequestDTO.getIdType());
        return new ResponseEntity<>(m, HttpStatus.OK);
    }
}
