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

    //Obtener todas las materias de una determinada carrera
    @GetMapping("/carreras/{idC}/materias")
    public ResponseEntity<List<Materia>> getMateriasByCarreraId(@PathVariable int idC) {
        try {
            List<Materia> materias = materiaService.getMateriasByCarreraId(idC);
            if (materias.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(materias);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Obtener todas las materias aprobadas de una carrera
    @GetMapping("/carreras/{idC}/aprobadas")
    public ResponseEntity<List<Materia>> getApprovedByCarreraId(@PathVariable int idC){
        try {
            List<Materia> materias = materiaService.getApprovedByCarreraId(idC);
           
            return ResponseEntity.ok(materias);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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

    // Crear todas las materias de un arreglo
    @PostMapping("/batch")
    public ResponseEntity<List<Materia>> createMateriasBatch(@RequestBody List<Materia> materias) {
        try {
            List <Materia> createdMaterias = materiaService.saveAllMaterias(materias);
            return ResponseEntity.ok(createdMaterias);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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
