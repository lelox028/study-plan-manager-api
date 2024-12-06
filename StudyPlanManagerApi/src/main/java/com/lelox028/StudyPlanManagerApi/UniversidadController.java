package com.lelox028.StudyPlanManagerApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UniversidadController {

    @Autowired
    private UniversidadService universidadService;

    @GetMapping("/universidades")
    public List<Universidad> getUniversidades(){
        return universidadService.getAllUniversidades();
    }
    
    @GetMapping("/universidad/{id}")
    public ResponseEntity<Universidad> getUniversidadById(@PathVariable int id){
        try {
            Universidad universidad = universidadService.getUniversidadById(id);
            return ResponseEntity.ok(universidad);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
