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

    public Universidad createUniversidad(Universidad newUniversidad){
        newUniversidad.setId_Universidad(0);
        if (universidadRepository.existsByNombreU(newUniversidad.getNombre_Universidad())) {
            throw new RuntimeException("Ya existe una universidad con el nombre: " + newUniversidad.getNombre_Universidad());
        }
        Universidad savedUniversidad = universidadRepository.save(newUniversidad);
        if (savedUniversidad == null){
            throw new RuntimeException("Could not create new Universidad");
        }else{
            return savedUniversidad;
        }
    }

    public Universidad updateUniversidad(int id, Universidad updatedUniversidad){
        Optional<Universidad> optionalUniversidad = universidadRepository.findById(id);
        if (optionalUniversidad.isPresent()){
            Universidad oldUniversidad = optionalUniversidad.get();
            oldUniversidad.setNombre_Universidad(updatedUniversidad.getNombre_Universidad());
            return universidadRepository.save(oldUniversidad);
        }
        else{
            throw new RuntimeException("Item not found with ID: " + id);
        }
    }
}
