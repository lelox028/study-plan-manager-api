package com.lelox028.StudyPlanManagerApi.Controllers;

import com.lelox028.StudyPlanManagerApi.Models.Usuario;
import com.lelox028.StudyPlanManagerApi.Services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/me")
    public ResponseEntity<Usuario> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario currentUser = (Usuario) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")  // Solo admins
    public ResponseEntity<List<Usuario>> allUsers() {
        List<Usuario> usuarios = usuarioService.allUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}