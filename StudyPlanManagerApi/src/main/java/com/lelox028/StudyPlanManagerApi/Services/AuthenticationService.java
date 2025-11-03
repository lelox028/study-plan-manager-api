package com.lelox028.StudyPlanManagerApi.Services;

import com.lelox028.StudyPlanManagerApi.DTOs.LoginUserDTO;  // Ajusta si es LoginUserDTO
import com.lelox028.StudyPlanManagerApi.DTOs.RegisterUserDTO;
import com.lelox028.StudyPlanManagerApi.Models.Rol;
import com.lelox028.StudyPlanManagerApi.Models.Usuario;
import com.lelox028.StudyPlanManagerApi.Repositories.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UsuarioRepository usuarioRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario signup(RegisterUserDTO input) {
        Usuario usuario = new Usuario();
        usuario.setUsername(input.getUsername());
        usuario.setEmail(input.getEmail());
        usuario.setPassword(passwordEncoder.encode(input.getPassword()));
        usuario.setRol(Rol.USER);  // Rol por defecto

        return usuarioRepository.save(usuario);
    }

    public Usuario authenticate(LoginUserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),  // Usa username
                        input.getPassword()
                )
        );

        return usuarioRepository.findByUsername(input.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}