package com.lelox028.StudyPlanManagerApi.Controllers;

import com.lelox028.StudyPlanManagerApi.DTOs.CarreraMateriasDTO;
import com.lelox028.StudyPlanManagerApi.Models.Carrera;
import com.lelox028.StudyPlanManagerApi.Models.Usuario;
import com.lelox028.StudyPlanManagerApi.Services.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    private Usuario getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Usuario) authentication.getPrincipal();
    }

    // Obtener todas las carreras
    @GetMapping
    public List<Carrera> getCarreras() {
        Usuario usuario = getAuthenticatedUser();
        return carreraService.getAllCarreras(usuario);
    }

    // Obtener una carrera por ID
    @GetMapping("/{id}")
    public ResponseEntity<Carrera> getCarreraById(@PathVariable int id) {
        Usuario usuario = getAuthenticatedUser();
        Carrera carrera = carreraService.getCarreraById(id, usuario);
        return ResponseEntity.ok(carrera);
    }

    // obtener todas las carreras de una determinada facultad
    @GetMapping("/facultades/{idF}/carreras")
    public ResponseEntity<List<Carrera>> getCarrerasByFacultadId(@PathVariable int idF) {
        Usuario usuario = getAuthenticatedUser();
        List<Carrera> carreras = carreraService.getCarrerasByFacultadId(idF, usuario);
        return ResponseEntity.ok(carreras);
    }

    // Crear una nueva carrera
    @PostMapping
    public ResponseEntity<Carrera> createCarrera(@Valid @RequestBody Carrera carrera) {
        Usuario usuario = getAuthenticatedUser();
        Carrera created = carreraService.createCarrera(carrera, usuario);
        return ResponseEntity.ok(created);
    }

    // Actualizar una carrera existente
    @PutMapping("/{id}")
    public ResponseEntity<Carrera> updateCarrera(@PathVariable int id, @Valid @RequestBody Carrera updatedCarrera) {
        Usuario usuario = getAuthenticatedUser();
        Carrera updated = carreraService.updateCarrera(id, updatedCarrera, usuario);
        return ResponseEntity.ok(updated);
    }

    // Eliminar una carrera existente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrera(@PathVariable int id) {
        Usuario usuario = getAuthenticatedUser();
        carreraService.deleteCarrera(id, usuario);
        return ResponseEntity.noContent().build();
    }

    //import a whole DTO
    @PostMapping("/import")
    public ResponseEntity<CarreraMateriasDTO> importCarreraWithMaterias(@RequestBody CarreraMateriasDTO carreraMateriasDTO) {
        try {
            CarreraMateriasDTO result;
            result = carreraService.importCarreraWithMaterias(carreraMateriasDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
