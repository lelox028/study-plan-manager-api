package com.lelox028.StudyPlanManagerApi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lelox028.StudyPlanManagerApi.Models.Carrera;
import com.lelox028.StudyPlanManagerApi.Services.CarreraService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    // Obtener todas las carreras
    @GetMapping
    public List<Carrera> getCarreras() {
        return carreraService.getAllCarreras();
    }

    // Obtener una carrera por ID
    @GetMapping("/{id}")
    public ResponseEntity<Carrera> getCarreraById(@PathVariable int id) {
        try {
            Carrera carrera = carreraService.getCarreraById(id);
            return ResponseEntity.ok(carrera);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // obtener todas las carreras de una determinada facultad
    @GetMapping("/facultades/{idF}/carreras")
    public ResponseEntity<List<Carrera>> getAllCarrerasByFacultadId(@PathVariable int idF) {
        try {
            List<Carrera> carreras = carreraService.getAllCarrerasByFacultadId(idF);
            if (carreras.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(carreras);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Crear una nueva carrera
    @PostMapping
    public ResponseEntity<?> createCarrera(@Valid @RequestBody Carrera carrera) {
        try {
            Carrera newCarrera = carreraService.createCarrera(carrera);
            return new ResponseEntity<>(newCarrera, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al crear la carrera: " + e.getMessage());
        }
    }

    // Actualizar una carrera existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarrera(@PathVariable int id, @Valid @RequestBody Carrera updatedCarrera) {
        try {
            Carrera carrera = carreraService.updateCarrera(id, updatedCarrera);
            return ResponseEntity.ok(carrera);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al actualizar la carrera: " + e.getMessage());
        }
    }

    // Eliminar una carrera existente
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarrera(@PathVariable int id) {
        try {
            carreraService.deleteCarrera(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al eliminar la carrera: " + e.getMessage());
        }
    }
}
