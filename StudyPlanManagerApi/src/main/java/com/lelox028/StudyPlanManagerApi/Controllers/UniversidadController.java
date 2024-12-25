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

import com.lelox028.StudyPlanManagerApi.Models.Universidad;
import com.lelox028.StudyPlanManagerApi.Services.UniversidadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/universidades")
public class UniversidadController {

    @Autowired
    private UniversidadService universidadService;

    @GetMapping
    public List<Universidad> getUniversidades() {
        return universidadService.getAllUniversidades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Universidad> getUniversidadById(@PathVariable int id) {
        try {
            Universidad universidad = universidadService.getUniversidadById(id);
            return ResponseEntity.ok(universidad);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createUniversidad(@Valid @RequestBody Universidad universidad) {
        try {
            Universidad newUniversidad = universidadService.createUniversidad(universidad);
            return new ResponseEntity<>(newUniversidad, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al crear la universidad: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Universidad> updateUniversidad(@PathVariable int id,
            @Valid @RequestBody Universidad updatedUniversidad) {
        try {
            Universidad newUniversidad = universidadService.updateUniversidad(id, updatedUniversidad);
            return ResponseEntity.ok(newUniversidad);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Universidad> deleteUniversidad(@PathVariable int id) {
        try {
            universidadService.deleteUniversidad(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
