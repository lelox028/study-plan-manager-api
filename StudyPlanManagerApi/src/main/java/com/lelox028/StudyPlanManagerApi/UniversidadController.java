package com.lelox028.StudyPlanManagerApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class UniversidadController {

    @Autowired
    private UniversidadService universidadService;

    @GetMapping("/universidades")
    public List<Universidad> getUniversidades() {
        return universidadService.getAllUniversidades();
    }

    @GetMapping("/universidad/{id}")
    public ResponseEntity<Universidad> getUniversidadById(@PathVariable int id) {
        try {
            Universidad universidad = universidadService.getUniversidadById(id);
            return ResponseEntity.ok(universidad);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("universidad")
    public ResponseEntity<?> createUniversidad(@Valid @RequestBody Universidad universidad) {
        try {
            Universidad newUniversidad = universidadService.createUniversidad(universidad);
            return new ResponseEntity<>(newUniversidad, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al crear la universidad: " + e.getMessage());
        }
    }

    @PutMapping("universidad/{id}")
    public ResponseEntity<Universidad> updateUniversidad(@PathVariable int id,
            @Valid @RequestBody Universidad updatedUniversidad) {
        try {
            Universidad newUniversidad = universidadService.updateUniversidad(id, updatedUniversidad);
            return ResponseEntity.ok(newUniversidad);

        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
