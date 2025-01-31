package com.bd.edu.co.mail_manager.Controller;

import com.bd.edu.co.mail_manager.DTO.AddContactDTO;
import com.bd.edu.co.mail_manager.Entity.Contacto;
import com.bd.edu.co.mail_manager.Service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/contact")
public class ContactController {
    @Autowired
    private ContactoService contactoService;

    @PostMapping("/add")
    public ResponseEntity<Contacto> addContact(@RequestBody AddContactDTO addContactDTO){
        Contacto c = contactoService.addContacto(addContactDTO.getContactUserId(), addContactDTO.getSelfUsername());
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Contacto>> getAllContacts(@RequestParam(name = "username") String username){
        List<Contacto> c = contactoService.getAllContacts(username);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
}
