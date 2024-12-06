package com.lelox028.StudyPlanManagerApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversidadService {

    @Autowired
    private UniversidadRepository universidadRepository;

    public List<Universidad> getAllUniversidades(){
        return universidadRepository.findAll();
    }
    
}
