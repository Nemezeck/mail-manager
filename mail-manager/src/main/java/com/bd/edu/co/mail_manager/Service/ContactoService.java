package com.bd.edu.co.mail_manager.Service;

import com.bd.edu.co.mail_manager.Entity.Contacto;
import com.bd.edu.co.mail_manager.Entity.Usuario;
import com.bd.edu.co.mail_manager.Repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactoService {
    @Autowired
    private ContactoRepository repository;
    @Autowired
    private UserService userService;

    private String createContactMail(String username, String domain){
        return username + "bd.edu" + domain;
    }

    public Contacto addContacto(Usuario contactUser, String selfUsername){

        Usuario u = userService.getUsuarioById(selfUsername);

        Contacto c = new Contacto();
        c.setCorreoContacto(createContactMail(contactUser.getUsuario(), contactUser.getPais().getDominio()));
        c.setNombreContacto(contactUser.getNombre());
        c.setUsuario(u);
        u.getContactos().add(c);


        return c;
    }
}
