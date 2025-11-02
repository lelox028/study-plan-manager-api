package com.lelox028.StudyPlanManagerApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lelox028.StudyPlanManagerApi.Models.Facultad;
import com.lelox028.StudyPlanManagerApi.Services.FacultadService;

import jakarta.validation.Valid;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lelox028.StudyPlanManagerApi.Models.Usuario;

@RestController
@RequestMapping("/facultades")
public class FacultadController {

    @Autowired
    private FacultadService facultadService;

    private Usuario getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Usuario) authentication.getPrincipal();
    }

    @GetMapping
    public List<Facultad> getFacultades() {
        Usuario usuario = getAuthenticatedUser();
        return facultadService.getAllFacultades(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facultad> getFacultadById(@PathVariable int id) {
        Usuario usuario = getAuthenticatedUser();
        Facultad facultad = facultadService.getFacultadById(id, usuario);
        return ResponseEntity.ok(facultad);
    }

    //Obtener todas las facultades de una determinada universidad
    @GetMapping("/universidades/{idU}/facultades")
    public ResponseEntity<List<Facultad>> getFacultadesbyUniversidadId(@PathVariable int idU) {
        Usuario usuario = getAuthenticatedUser();
        List<Facultad> facultades = facultadService.getFacultadesbyUniversidadId(idU, usuario);
        return ResponseEntity.ok(facultades);
    }

    @PostMapping
    public ResponseEntity<?> createFacultad(@Valid @RequestBody Facultad facultad) {
        Usuario usuario = getAuthenticatedUser();
        Facultad created = facultadService.createFacultad(facultad, usuario);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facultad> updateFacultad(@PathVariable int id, @Valid @RequestBody Facultad updatedFacultad) {
        Usuario usuario = getAuthenticatedUser();
        Facultad updated = facultadService.updateFacultad(id, updatedFacultad, usuario);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacultad(@PathVariable int id) {
        Usuario usuario = getAuthenticatedUser();
        facultadService.deleteFacultad(id, usuario);
        return ResponseEntity.noContent().build();
    }
}
