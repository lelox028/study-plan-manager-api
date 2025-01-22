package com.lelox028.StudyPlanManagerApi.Services;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lelox028.StudyPlanManagerApi.Models.*;
import com.lelox028.StudyPlanManagerApi.Repositories.*;
import com.lelox028.StudyPlanManagerApi.DTOs.*;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private FacultadRepository facultadRepository;

    @Autowired
    private UniversidadRepository universidadRepository;

    @Autowired
    private MateriaRepository materiaRepository;;

    // Obtener todas las carreras
    public List<Carrera> getAllCarreras() {
        return carreraRepository.findAll();
    }

    // Obtener todas las carreras de una facultad particular
    public List<Carrera> getAllCarrerasByFacultadId(int idF) {
        Optional<Facultad> optionalFacultad = facultadRepository.findById(idF);
        if (optionalFacultad.isPresent()) {
            Facultad facultad = optionalFacultad.get();
            return carreraRepository.findByFacultad(facultad);
        } else {
            throw new RuntimeException("No existen carreras dictadas por la facultad con ID: " + idF);
        }
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

    // import a whole DTO containing a Carrera, facultad, Universidad and a list of
    // Materias.
    @Transactional
    public CarreraMateriasDTO importCarreraWithMaterias(CarreraMateriasDTO carreraMateriasDTO) {
        Carrera newCarrera = carreraMateriasDTO.getCarrera();
        List<Materia> newMaterias = carreraMateriasDTO.getMaterias();

        // 1. Guardar o verificar universidad

        Universidad tempUniversidad = newCarrera.getFacultad().getUniversidad();
        Universidad newUniversidad = universidadRepository.findByNombreU(tempUniversidad.getNombre_Universidad())
                .orElseGet(() -> universidadRepository.save(tempUniversidad));

        // 2. Guardar o verificar facultad
        Facultad tempFacultad = newCarrera.getFacultad();
        tempFacultad.setUniversidad(newUniversidad);
        Facultad newFacultad = facultadRepository.findByNombreFAndUniversidad(tempFacultad.getNombreF(), newUniversidad)
                .orElseGet(() -> facultadRepository.save(tempFacultad));

        // 3. Guardar o verificar carrera
        Carrera tempCarrera = newCarrera;
        tempCarrera.setFacultad(newFacultad);
        newCarrera = carreraRepository.findByNombreCAndFacultad(tempCarrera.getNombreC(), newFacultad)
                .orElseGet(() -> carreraRepository.save(tempCarrera));

        // 4. Asociar materias a la carrera
        for (Materia materia : newMaterias) {
            Set<Materia> newCorrelativas = new HashSet<>();
            materia.setCarrera(newCarrera);
            // revisar correlativas
            for (Materia correlativa : Optional.ofNullable(materia.getCorrelativas()).orElse(Collections.emptySet())) {
                Optional<Materia> optionalMateria = materiaRepository
                        .findByNombreMAndCarrera(correlativa.getNombreMateria(), newCarrera);
                if (optionalMateria.isPresent()) {
                    newCorrelativas.add(optionalMateria.get());
                }
            }
            materia.setCorrelativas(newCorrelativas);
            materiaRepository.save(materia);
        }
        CarreraMateriasDTO result = new CarreraMateriasDTO(newCarrera, newMaterias);
        return result;
    }

    // Actualizar una carrera existente
    public Carrera updateCarrera(int id, Carrera updatedCarrera) {
        Optional<Carrera> optionalCarrera = carreraRepository.findById(id);
        if (optionalCarrera.isPresent()) {
            Carrera existingCarrera = optionalCarrera.get();
            updatedCarrera.setId_C(id); // Settear el identificador al valor correcto
            Field[] fields = Carrera.class.getDeclaredFields(); // Obtener todos los campos de la clase Carrera
            for (Field field : fields) {
                try {
                    field.setAccessible(true); // Permitir acceso a campos privados
                    Object newValue = field.get(updatedCarrera); // Obtener el valor del campo correspondiente en
                                                                 // updatedCarrera
                    field.set(existingCarrera, newValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); // Manejar posibles excepciones
                }
            }
            return carreraRepository.save(existingCarrera);

        } else {
            throw new RuntimeException("No se encontró la carrera con ID: " + id);
        }
    }

    // Eliminar una carrera existente
    @Transactional
    public void deleteCarrera(int id) {
        Optional<Carrera> optionalCarrera = carreraRepository.findById(id);
        if (optionalCarrera.isPresent()) {

            Carrera carrera = optionalCarrera.get();
            List<Materia> materias = materiaRepository.findByCarrera(carrera);

            for (Materia materia : materias) {
                materiaRepository.deleteById(materia.getIdMateria());
            }
            carreraRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se encontró la carrera con ID: " + id);
        }
    }
}
