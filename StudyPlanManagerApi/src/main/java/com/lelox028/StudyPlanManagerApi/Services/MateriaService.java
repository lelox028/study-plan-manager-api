package com.lelox028.StudyPlanManagerApi.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lelox028.StudyPlanManagerApi.Models.Materia;
import com.lelox028.StudyPlanManagerApi.Models.Carrera;
import com.lelox028.StudyPlanManagerApi.Repositories.MateriaRepository;
import com.lelox028.StudyPlanManagerApi.Repositories.CarreraRepository;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    public List<Materia> getAllMaterias() {
        return materiaRepository.findAll();
    }

    public Materia getMateriaById(int id) {
        Optional<Materia> optionalMateria = materiaRepository.findById(id);
        if (optionalMateria.isPresent()) {
            return optionalMateria.get();
        } else
            throw new RuntimeException("No se encontro la materia con ID: " + id);
    }

    public List<Materia> getMateriasByCarreraId(int idC) {
        Optional<Carrera> optionalCarrera = carreraRepository.findById(idC);
        if (optionalCarrera.isPresent()) {
            Carrera carrera = optionalCarrera.get();
            return materiaRepository.findByCarrera(carrera);
        } else
            throw new RuntimeException("No se encontro la Carrera con ID: " + idC);
    }

    // Get Approved by Carrera
    public List<Materia> getApprovedByCarreraId(int idC) {
        Optional<Carrera> optionalCarrera = carreraRepository.findById(idC);
        if (optionalCarrera.isPresent()) {
            return materiaRepository.getApprovedByCarrera(idC);
        } else
            throw new RuntimeException("No se encontro la Carrera con ID: " + idC);
    }

    public Materia createMateria(Materia newMateria) { // validar cuatrimestre
        newMateria.setIdMateria(0);

        // Validar que la Carrera exista
        Optional<Carrera> optionalCarrera = carreraRepository.findById(newMateria.getCarrera().getId_C());
        if (!optionalCarrera.isPresent()) {
            throw new RuntimeException(
                    "No se encontró la Carrera con el ID: " + newMateria.getCarrera().getId_C());
        } else {
            // si el id de la carrera existe, me aseguro de que la materia nueva se guarde
            // con todos los datos de la carrera correspondiente al id recibido.
            Carrera tempCarrera = optionalCarrera.get();
            newMateria.setCarrera(tempCarrera);
        }

        // Validar que el nombre no se repita en la misma Carrera
        if (materiaRepository.existsByNombreMAndCarrera(newMateria.getNombreMateria(), newMateria.getCarrera())) {
            throw new RuntimeException("Ya existe una Materia con el nombre '" + newMateria.getNombreMateria() +
                    "' en la Carrera con ID: " + newMateria.getCarrera().getId_C());
        }

        newMateria.setCarrera(optionalCarrera.get());
        Materia savedMateria = materiaRepository.save(newMateria);

        if (savedMateria == null) {
            throw new RuntimeException("No se pudo crear la nueva Materia");
        } else {
            return savedMateria;
        }
    }

    public Materia updateMateria(int id, Materia updatedMateria) { // validar cuatrimestre
        Optional<Materia> optionalMateria = materiaRepository.findById(id);
        if (optionalMateria.isPresent()) {
            Materia existingMateria = optionalMateria.get();
            existingMateria.setNombreMateria(updatedMateria.getNombreMateria());
            existingMateria.setAnio(updatedMateria.getAnio());
            existingMateria.setCuatrimestre(updatedMateria.getCuatrimestre());
            existingMateria.setEstado(updatedMateria.getEstado());
            existingMateria.setFechaAprobacion(updatedMateria.getFechaAprobacion());
            existingMateria.setCalificacion(updatedMateria.getCalificacion());
            existingMateria.setFechaRegularizacion(updatedMateria.getFechaRegularizacion());
            existingMateria.setRequeridaPorTituloIntermedio(updatedMateria.isRequeridaPorTituloIntermedio());
            existingMateria.setCorrelativas(updatedMateria.getCorrelativas());

            // Validar que el nombre no se repita en la misma Carrera al actualizar
            if (materiaRepository.existsByNombreMAndCarrera(updatedMateria.getNombreMateria(),
                    updatedMateria.getCarrera())) {
                throw new RuntimeException("Ya existe una Materia con el nombre '" + updatedMateria.getNombreMateria() +
                        "' en la Carrera ID: " + updatedMateria.getCarrera().getId_C());
            }

            return materiaRepository.save(existingMateria);
        } else {
            throw new RuntimeException("No se encontró una Materia con el ID: " + id);
        }
    }

    public void deleteMateria(int id) {
        Optional<Materia> optionalMateria = materiaRepository.findById(id);
        if (optionalMateria.isPresent()) {
            materiaRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró una Materia con el ID: " + id);
        }
    }
}
