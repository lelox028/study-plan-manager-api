package com.lelox028.StudyPlanManagerApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UniversidadController {

    @Autowired
    private UniversidadService universidadService;

    @GetMapping("/universidades")
    public List<Universidad> getUniversidades(){
        return universidadService.getAllUniversidades();
    }
    
}
