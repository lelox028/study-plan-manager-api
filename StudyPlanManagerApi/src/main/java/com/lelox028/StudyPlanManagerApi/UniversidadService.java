package com.lelox028.StudyPlanManagerApi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversidadService {

    @Autowired
    private UniversidadRepository universidadRepository;

    public List<Universidad> getAllUniversidades(){
        return universidadRepository.findAll();
    }
    
    public Universidad getUniversidadById(int id){
        Optional<Universidad> optionalUniversidad = universidadRepository.findById(id);
        if (optionalUniversidad.isPresent()){
            return optionalUniversidad.get();
        }
        else{
            throw new RuntimeException("Cannot get item with ID: "+ id);
        }
    }
}
