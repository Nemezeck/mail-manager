package com.bd.edu.co.mail_manager.Service;

import com.bd.edu.co.mail_manager.Entity.Contacto;
import com.bd.edu.co.mail_manager.Entity.Usuario;
import com.bd.edu.co.mail_manager.Repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactoService {
    @Autowired
    private ContactoRepository repository;
    @Autowired
    private UserService userService;

    private String createContactMail(String username, String domain){
        return username + "@bd.edu" + domain;
    }

    public Contacto addContacto(String contactUserId, String selfUsername){

        Usuario u = userService.getUsuarioById(selfUsername);
        Usuario contactUser = userService.getUsuarioById(contactUserId);

        if(u.getContactos().stream().anyMatch(contacto -> contacto.getUsuario().getUsuario().equals(selfUsername)))
            throw new RuntimeException("User is already a contact");

        Contacto c = new Contacto();
        c.setCorreoContacto(createContactMail(contactUser.getUsuario(), contactUser.getPais().getDominio()));
        c.setNombreContacto(contactUser.getNombre());
        c.setUsuario(u);

        repository.insertContacto(c.getCorreoContacto(), c.getNombreContacto(), c.getUsuario().getUsuario());

        Contacto persistedContact = getContactoByMail(c.getCorreoContacto());
        u.getContactos().add(persistedContact);
        userService.updateUser(u);

        return c;
    }

    public List<Contacto> getContactosByMail(List<String> mails){
        List<Contacto> contactos = new ArrayList<>();
        if(!mails.isEmpty()) {
            for (String mail : mails) {
                contactos.add(repository.findByMail(mail));
            }
            return contactos;
        }
        else{
            throw new RuntimeException("Mails are empty");
        }
    }

    public Contacto getContactoByMail(String mail){
        return repository.findByMail(mail);
    }

    public List<Contacto> getAllContacts(String username) {
        Usuario u = userService.getUsuarioById(username);

        if (u == null) {
            throw new RuntimeException("User not found");
        }

        if (!u.getContactos().isEmpty()) {
            return u.getContactos();
        } else {
            throw new RuntimeException("User has no contacts");
        }
    }
}
