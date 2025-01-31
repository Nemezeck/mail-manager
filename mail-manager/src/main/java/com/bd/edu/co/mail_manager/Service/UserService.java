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

        userRepository.insertUser(u.getUsuario(), u.getEstado().getIdEstado(), u.getPais().getIdPais(), u.getNombre(), u.getApellido(),
        u.getFechaNacimiento(), u.getFechaCreacion(), u.getCorreoAlterno(), u.getCelular(), u.getPassword());
    }

    public Usuario getUsuarioById(String id){
        return userRepository.getUserByIdNative(id);
    }

    private String createUsername(String name, String lastName){
        String username = name.charAt(0) + lastName.substring(0,5);
        if(userRepository.findById(username).isPresent()){
            if(Character.isDigit(username.charAt(username.length() - 1))){
                int newDigit = Character.getNumericValue(username.charAt(username.length() - 1));
                char newChar = (char) ('0' + newDigit);
                return username + newChar;
            }
            else{
                return username.substring(0, username.length()-2) + "1";
            }
        }
        else {
            return username;
        }
    }

    private void updateUser(Usuario u){
        userRepository.save(u);
    }
}
