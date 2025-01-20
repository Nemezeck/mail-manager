package com.bd.edu.co.mail_manager.Controller;

import com.bd.edu.co.mail_manager.DTO.UsuarioDTO;
import com.bd.edu.co.mail_manager.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UsuarioDTO userDTO) {
        userService.register(userDTO);
        return ResponseEntity.ok("User created successfully");
    }
}
