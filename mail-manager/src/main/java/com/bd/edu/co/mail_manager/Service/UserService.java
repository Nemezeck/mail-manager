package com.bd.edu.co.mail_manager.Service;

import com.bd.edu.co.mail_manager.DTO.AuthenticatedUser;
import com.bd.edu.co.mail_manager.DTO.UsuarioDTO;
import com.bd.edu.co.mail_manager.Entity.Usuario;
import com.bd.edu.co.mail_manager.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PaisService paisService;

    @Autowired
    private EstadoService estadoService;

    @Transactional
    public void register(UsuarioDTO userDTO) {

        Usuario u = new Usuario();
        u.setNombre(userDTO.getNombre());
        u.setApellido(userDTO.getApellido());
        u.setFechaCreacion(Date.valueOf(LocalDate.now()));
        u.setCelular(userDTO.getCelular());
        u.setFechaNacimiento(userDTO.getFechaNacimiento());
        u.setCorreoAlterno(userDTO.getCorreoAlterno());
        u.setUsuario(createUsername(userDTO.getNombre(), userDTO.getApellido()));
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setPais(paisService.getPaisById(userDTO.getIdPais()));
        u.setEstado(estadoService.getEstadoById(userDTO.getIdEstado()));
        u.setContactos(new ArrayList<>());

        userRepository.insertUser(u.getUsuario(), u.getEstado().getIdEstado(), u.getPais().getIdPais(), u.getNombre(), u.getApellido(),
        u.getFechaNacimiento(), u.getFechaCreacion(), u.getCorreoAlterno(), u.getCelular(), u.getPassword());
    }

    public Usuario getUsuarioById(String id){
        return userRepository.getUserByIdNative(id);
    }

    private String createUsername(String name, String lastName) {
        if (name == null || name.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Name and LastName cannot be empty");
        }

        String baseUsername = name.charAt(0) + (lastName.length() >= 4 ? lastName.substring(0, 4) : lastName);

        String username = baseUsername;
        int counter = 1;

        while (userRepository.findById(username).isPresent()) {
            username = baseUsername + counter;
            counter++;
        }

        return username;
    }


    public void updateUser(Usuario u){
        userRepository.save(u);
    }

    public Usuario findByMail(String mail){
        char delimiter = '@';

        int index = mail.indexOf(delimiter);

        if (index != -1) {
            String result = mail.substring(0, index);
            return getUsuarioById(result);
        } else {
            throw new RuntimeException("Not valid mail");
        }
    }

    public String getPaisByUsername(String username){
        return userRepository.getPaisIdByUsername(username);
    }
}
