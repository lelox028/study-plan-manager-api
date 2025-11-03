package com.lelox028.StudyPlanManagerApi.Controllers;

import com.lelox028.StudyPlanManagerApi.Models.Universidad;
import com.lelox028.StudyPlanManagerApi.Models.Usuario;
import com.lelox028.StudyPlanManagerApi.Services.UniversidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universidades")
public class UniversidadController {

    @Autowired
    private UniversidadService universidadService;

    // Método helper para obtener usuario autenticado
    private Usuario getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Usuario) authentication.getPrincipal();
    }

    @GetMapping
    public List<Universidad> getUniversidades() {
        Usuario usuario = getAuthenticatedUser();
        return universidadService.getAllUniversidades(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Universidad> getUniversidadById(@PathVariable int id) {
        try {
            Usuario usuario = getAuthenticatedUser();
            Universidad universidad = universidadService.getUniversidadById(id, usuario);
            return ResponseEntity.ok(universidad);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createUniversidad( @RequestBody Universidad universidad) {
        try {
            Usuario usuario = getAuthenticatedUser();
            Universidad created = universidadService.createUniversidad(universidad, usuario);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al crear la universidad: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUniversidad(@PathVariable int id, @RequestBody Universidad updatedUniversidad) {
        try {
            Usuario usuario = getAuthenticatedUser();
            Universidad updated = universidadService.updateUniversidad(id, updatedUniversidad, usuario);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al actualizar la universidad: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversidad(@PathVariable int id) {
        Usuario usuario = getAuthenticatedUser();
        universidadService.deleteUniversidad(id, usuario);
        return ResponseEntity.noContent().build();
    }
}
