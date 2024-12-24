package com.lelox028.StudyPlanManagerApi.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lelox028.StudyPlanManagerApi.Models.Carrera;
import com.lelox028.StudyPlanManagerApi.Models.Facultad;
import com.lelox028.StudyPlanManagerApi.Repositories.CarreraRepository;
import com.lelox028.StudyPlanManagerApi.Repositories.FacultadRepository;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private FacultadRepository facultadRepository;

    // Obtener todas las carreras
    public List<Carrera> getAllCarreras() {
        return carreraRepository.findAll();
    }

    // Obtener todas las carreras de una facultad particular
    public List<Carrera> getAllCarrerasByFacultadId(int idF){
        return carreraRepository.findByFacultad_Id(idF);
    }

    // Obtener una carrera por ID
    public Carrera getCarreraById(int id) {
        Optional<Carrera> optionalCarrera = carreraRepository.findById(id);
        if (optionalCarrera.isPresent()) {
            return optionalCarrera.get();
        } else {
            throw new RuntimeException("No se encontró la carrera con ID: " + id);
        }
    }

    // Crear una nueva carrera
    public Carrera createCarrera(Carrera newCarrera) {
        newCarrera.setId_C(0); // Forzar la creación de una nueva carrera

        // Validar que la facultad exista
        Optional<Facultad> optionalFacultad = facultadRepository.findById(newCarrera.getFacultad().getId_F());
        if (!optionalFacultad.isPresent()) {
            throw new RuntimeException(
                    "No se encontró la facultad con el ID: " + newCarrera.getFacultad().getId_F());
        } else {
            // si el id de la facultad existe, me aseguro de que la carrera nueva se guarde
            // con todos los datos de la facultad correspondiente al id recibido.
            Facultad tempfFacultad = optionalFacultad.get();
            newCarrera.setFacultad(tempfFacultad);
        }

        // Validar que el nombre no se repita en la misma facultad
        if (carreraRepository.existsByNombreCAndFacultad(newCarrera.getNombreC(), newCarrera.getFacultad())) {
            throw new RuntimeException("Ya existe una carrera con el nombre '" + newCarrera.getNombreC() +
                    "' en la facultad ID: " + newCarrera.getFacultad().getId_F());
        }

        Carrera savedCarrera = carreraRepository.save(newCarrera);
        if (savedCarrera == null) {
            throw new RuntimeException("No se pudo crear la nueva carrera");
        } else {
            return savedCarrera;
        }
    }

    // Actualizar una carrera existente
    public Carrera updateCarrera(int id, Carrera updatedCarrera) {
        Optional<Carrera> optionalCarrera = carreraRepository.findById(id);
        if (optionalCarrera.isPresent()) {
            Carrera existingCarrera = optionalCarrera.get();
            existingCarrera.setNombreC(updatedCarrera.getNombreC());
            existingCarrera.setFechaInscripcion(updatedCarrera.getFechaInscripcion());
            existingCarrera.setDuracion(updatedCarrera.getDuracion());
            existingCarrera.setPlan(updatedCarrera.getPlan());
            existingCarrera.setTituloIntermedio(updatedCarrera.getTituloIntermedio());
            existingCarrera.setFacultad(updatedCarrera.getFacultad());

            // Validar que el nombre no se repita en la misma facultad al actualizar
            if (carreraRepository.existsByNombreCAndFacultad(updatedCarrera.getNombreC(),
                    updatedCarrera.getFacultad())) {
                throw new RuntimeException("Ya existe una facultad con el nombre '" + updatedCarrera.getNombreC() +
                        "' en la facultad ID: " + updatedCarrera.getFacultad().getId_F());
            }

            return carreraRepository.save(existingCarrera);

        } else {
            throw new RuntimeException("No se encontró la carrera con ID: " + id);
        }
    }

    // Eliminar una carrera existente
    public void deleteCarrera(int id) {
        Optional<Carrera> optionalCarrera = carreraRepository.findById(id);
        if (optionalCarrera.isPresent()) {
            carreraRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró la carrera con ID: " + id);
        }
    }
}
