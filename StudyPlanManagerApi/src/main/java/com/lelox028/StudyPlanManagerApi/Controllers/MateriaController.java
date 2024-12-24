package com.lelox028.StudyPlanManagerApi.Controllers;

import com.lelox028.StudyPlanManagerApi.Models.Materia;
import com.lelox028.StudyPlanManagerApi.Services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    // Obtener todas las materias
    @GetMapping
    public List<Materia> getAllMaterias() {
        return materiaService.getAllMaterias();
    }

    // Obtener materia por ID
    @GetMapping("/{id}")
    public ResponseEntity<Materia> getMateriaById(@PathVariable int id) {
        try {
            Materia materia = materiaService.getMateriaById(id);
            return new ResponseEntity<>(materia, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Crear una nueva materia
    @PostMapping
    public ResponseEntity<Materia> createMateria(@RequestBody Materia newMateria) {
        try {
            Materia createdMateria = materiaService.createMateria(newMateria);
            return new ResponseEntity<>(createdMateria, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Actualizar una materia existente
    @PutMapping("/{id}")
    public ResponseEntity<Materia> updateMateria(@PathVariable int id, @RequestBody Materia updatedMateria) {
        try {
            Materia updated = materiaService.updateMateria(id, updatedMateria);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una materia por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMateria(@PathVariable int id) {
        try {
            materiaService.deleteMateria(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
