package com.lelox028.StudyPlanManagerApi.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lelox028.StudyPlanManagerApi.Models.Facultad;
import com.lelox028.StudyPlanManagerApi.Models.Universidad;
import com.lelox028.StudyPlanManagerApi.Repositories.FacultadRepository;
import com.lelox028.StudyPlanManagerApi.Repositories.UniversidadRepository;

@Service
public class FacultadService {

    @Autowired
    private FacultadRepository facultadRepository;

    @Autowired
    private UniversidadRepository universidadRepository;

    public List<Facultad> getAllFacultades() {
        return facultadRepository.findAll();
    }

    public Facultad getFacultadById(int id) {
        Optional<Facultad> optionalFacultad = facultadRepository.findById(id);
        if (optionalFacultad.isPresent()) {
            return optionalFacultad.get();
        } else {
            throw new RuntimeException("No se encontró una facultad con el ID: " + id);
        }
    }

    public Facultad createFacultad(Facultad newFacultad) {
        newFacultad.setId_F(0);

        // Validar que la universidad exista
        Optional<Universidad> optionalUniversidad = universidadRepository.findById(newFacultad.getUniversidad().getId_Universidad());
        if (!optionalUniversidad.isPresent()) {
            throw new RuntimeException(
                    "No se encontró la universidad con el ID: " + newFacultad.getUniversidad().getId_Universidad());
        }
        else{
            // si el id de la universidad existe, me aseguro de que la facultad nueva se guarde con todos los datos de la universidad correspondiente al id recibido.
            Universidad tempUniversidad = optionalUniversidad.get();
            newFacultad.setUniversidad(tempUniversidad);
        }

        // Validar que el nombre no se repita en la misma universidad
        if (facultadRepository.existsByNombreFAndUniversidad(newFacultad.getNombreF(), newFacultad.getUniversidad())) {
            throw new RuntimeException("Ya existe una facultad con el nombre '" + newFacultad.getNombreF() +
                    "' en la universidad ID: " + newFacultad.getUniversidad().getId_Universidad());
        }

        newFacultad.setUniversidad(optionalUniversidad.get());
        Facultad savedFacultad = facultadRepository.save(newFacultad);

        if (savedFacultad == null) {
            throw new RuntimeException("No se pudo crear la nueva facultad");
        } else {
            return savedFacultad;
        }
    }

    public Facultad updateFacultad(int id, Facultad updatedFacultad) {
        Optional<Facultad> optionalFacultad = facultadRepository.findById(id);
        if (optionalFacultad.isPresent()) {
            Facultad existingFacultad = optionalFacultad.get();
            existingFacultad.setNombreF(updatedFacultad.getNombreF());

            // Validar que el nombre no se repita en la misma universidad al actualizar
            if (facultadRepository.existsByNombreFAndUniversidad(updatedFacultad.getNombreF(),
                    updatedFacultad.getUniversidad())) {
                throw new RuntimeException("Ya existe una facultad con el nombre '" + updatedFacultad.getNombreF() +
                        "' en la universidad ID: " + updatedFacultad.getUniversidad().getId_Universidad());
            }

            return facultadRepository.save(existingFacultad);
        } else {
            throw new RuntimeException("No se encontró una facultad con el ID: " + id);
        }
    }

    public void deleteFacultad(int id) {
        Optional<Facultad> optionalFacultad = facultadRepository.findById(id);
        if (optionalFacultad.isPresent()) {
            facultadRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró una facultad con el ID: " + id);
        }
    }
}
