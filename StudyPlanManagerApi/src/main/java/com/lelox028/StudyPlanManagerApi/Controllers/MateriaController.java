package com.lelox028.StudyPlanManagerApi.Controllers;

import com.lelox028.StudyPlanManagerApi.Models.Materia;
import com.lelox028.StudyPlanManagerApi.Models.Usuario;
import com.lelox028.StudyPlanManagerApi.Services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    private Usuario getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Usuario) authentication.getPrincipal();
    }

    // Obtener todas las materias
    @GetMapping
    public List<Materia> getAllMaterias() {
        Usuario usuario = getAuthenticatedUser();
        return materiaService.getAllMaterias(usuario);
    }

    // Obtener materia por ID
    @GetMapping("/{id}")
    public ResponseEntity<Materia> getMateriaById(@PathVariable int id) {
        Usuario usuario = getAuthenticatedUser();
        try {
            Materia materia = materiaService.getMateriaById(id, usuario);
            return new ResponseEntity<>(materia, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Obtener todas las materias de una determinada carrera
    @GetMapping("/carreras/{idC}/materias")
    public ResponseEntity<List<Materia>> getMateriasByCarreraId(@PathVariable int idC) {
        Usuario usuario = getAuthenticatedUser();
        try {
            List<Materia> materias = materiaService.getMateriasByCarreraId(idC, usuario);
            return ResponseEntity.ok(materias);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Obtener todas las materias aprobadas de una carrera
    @GetMapping("/carreras/{idC}/aprobadas")
    public ResponseEntity<List<Materia>> getApprovedByCarreraId(@PathVariable int idC) {
        Usuario usuario = getAuthenticatedUser();
        try {
            List<Materia> materias = materiaService.getApprovedByCarreraId(idC, usuario);
            return ResponseEntity.ok(materias);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear una nueva materia
    @PostMapping
    public ResponseEntity<Materia> createMateria(@RequestBody Materia newMateria) {
        Usuario usuario = getAuthenticatedUser();
        try {
            Materia created = materiaService.createMateria(newMateria, usuario);
            return ResponseEntity.ok(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Crear todas las materias de un arreglo (batch)
    @PostMapping("/batch")
    public ResponseEntity<List<Materia>> createMateriasBatch(@RequestBody List<Materia> materias) {
        Usuario usuario = getAuthenticatedUser();
        try {
            List<Materia> createdMaterias = materiaService.saveAllMaterias(materias, usuario);
            return ResponseEntity.ok(createdMaterias);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Actualizar una materia existente
    @PutMapping("/{id}")
    public ResponseEntity<Materia> updateMateria(@PathVariable int id, @RequestBody Materia updatedMateria) {
        Usuario usuario = getAuthenticatedUser();
        try {
            Materia updated = materiaService.updateMateria(id, updatedMateria, usuario);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una materia por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable int id) {
        Usuario usuario = getAuthenticatedUser();
        try {
            materiaService.deleteMateria(id, usuario);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
