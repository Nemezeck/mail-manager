package com.bd.edu.co.mail_manager.Controller;

import com.bd.edu.co.mail_manager.DTO.AuthenticatedUser;
import com.bd.edu.co.mail_manager.DTO.LoginRequest;
import com.bd.edu.co.mail_manager.DTO.UsuarioDTO;
import com.bd.edu.co.mail_manager.Entity.Contacto;
import com.bd.edu.co.mail_manager.Entity.Pais;
import com.bd.edu.co.mail_manager.Repository.PaisRepository;
import com.bd.edu.co.mail_manager.Service.UserService;
import org.apache.catalina.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PaisRepository repository;


    private final AuthenticationManager authenticationManager;
    public UserController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UsuarioDTO userDTO) {
        userService.register(userDTO);
        return ResponseEntity.ok("User created successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody LoginRequest loginRequest) {
        // Create an authentication token using the provided username and password
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        // Authenticate the user

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // Store the authentication in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Map<String, String > m = new HashMap<>();
        m.put(loginRequest.getUsername(), loginRequest.getPassword());

        return new ResponseEntity<>(m, HttpStatus.OK);
    }

}
