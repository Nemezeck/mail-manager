package com.bd.edu.co.mail_manager.Service;

import com.bd.edu.co.mail_manager.DTO.AuthenticatedUser;
import com.bd.edu.co.mail_manager.Entity.Usuario;
import com.bd.edu.co.mail_manager.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository usuarioRepository;

    public CustomUserDetailsService(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the database
        Usuario usuario = usuarioRepository.findById(username).orElse(null);

        if (usuario == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Return the custom AuthenticatedUser object
        return new AuthenticatedUser(usuario);
    }
}
