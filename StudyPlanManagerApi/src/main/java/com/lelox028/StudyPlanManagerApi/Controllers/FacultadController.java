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
import org.springframework.web.bind.annotation.RestController;

import com.lelox028.StudyPlanManagerApi.Models.Facultad;
import com.lelox028.StudyPlanManagerApi.Services.FacultadService;

import jakarta.validation.Valid;

@RestController
public class FacultadController {

    @Autowired
    private FacultadService facultadService;

    @GetMapping("/facultades")
    public List<Facultad> getFacultades() {
        return facultadService.getAllFacultades();
    }

    @GetMapping("/facultad/{id}")
    public ResponseEntity<Facultad> getFacultadById(@PathVariable int id) {
        try {
            Facultad facultad = facultadService.getFacultadById(id);
            return ResponseEntity.ok(facultad);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/facultad")
    public ResponseEntity<?> createFacultad(@Valid @RequestBody Facultad facultad) {
        try {
            Facultad newFacultad = facultadService.createFacultad(facultad);
            return new ResponseEntity<>(newFacultad, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al crear la facultad: " + e.getMessage());
        }
    }

    @PutMapping("/facultad/{id}")
    public ResponseEntity<Facultad> updateFacultad(@PathVariable int id, @Valid @RequestBody Facultad updatedFacultad) {
        try {
            Facultad updated = facultadService.updateFacultad(id, updatedFacultad);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/facultad/{id}")
    public ResponseEntity<Void> deleteFacultad(@PathVariable int id) {
        try {
            facultadService.deleteFacultad(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
