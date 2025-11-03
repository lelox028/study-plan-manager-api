package com.lelox028.StudyPlanManagerApi.Services;


import com.lelox028.StudyPlanManagerApi.DTOs.CarreraMateriasDTO;
import com.lelox028.StudyPlanManagerApi.Models.Carrera;
import com.lelox028.StudyPlanManagerApi.Models.Facultad;
import com.lelox028.StudyPlanManagerApi.Models.Materia;
import com.lelox028.StudyPlanManagerApi.Models.Universidad;
import com.lelox028.StudyPlanManagerApi.Models.Usuario;
import com.lelox028.StudyPlanManagerApi.Repositories.CarreraRepository;
import com.lelox028.StudyPlanManagerApi.Repositories.FacultadRepository;
import com.lelox028.StudyPlanManagerApi.Repositories.MateriaRepository;
import com.lelox028.StudyPlanManagerApi.Repositories.UniversidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Objects;  // Agregado

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private FacultadRepository facultadRepository;

    @Autowired
    private UniversidadRepository universidadRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    // Obtener todas las carreras del usuario (a través de sus universidades/facultades)
    public List<Carrera> getAllCarreras(Usuario usuario) {
        List<Universidad> universidades = universidadRepository.findByUsuario(usuario);
        List<Facultad> facultades = facultadRepository.findByUniversidadIn(universidades);
        return carreraRepository.findByFacultadIn(facultades);
    }

    // Obtener carrera por ID, solo si pertenece a una facultad del usuario
    public Carrera getCarreraById(int id, Usuario usuario) {
        Optional<Carrera> optionalCarrera = carreraRepository.findById(id);
        if (optionalCarrera.isPresent()) {
            Carrera carrera = optionalCarrera.get();
            if (Objects.equals(carrera.getFacultad().getUniversidad().getUsuario().getIdUsuarios(), usuario.getIdUsuarios())) {
                return carrera;
            }
        }
        throw new RuntimeException("Carrera no encontrada o no pertenece al usuario");
    }

    // Obtener carreras por facultad ID, solo si la facultad pertenece al usuario
    public List<Carrera> getCarrerasByFacultadId(int idF, Usuario usuario) {
        Optional<Facultad> optionalFacultad = facultadRepository.findById(idF);
        if (optionalFacultad.isPresent() && Objects.equals(optionalFacultad.get().getUniversidad().getUsuario().getIdUsuarios(), usuario.getIdUsuarios())) {
            return carreraRepository.findByFacultad(optionalFacultad.get());
        }
        throw new RuntimeException("Facultad no encontrada o no pertenece al usuario");
    }

    // Crear carrera, asignada a una facultad del usuario
    public Carrera createCarrera(Carrera newCarrera, Usuario usuario) {
        Facultad facultad = newCarrera.getFacultad();
        if (facultad == null || !Objects.equals(facultad.getUniversidad().getUsuario().getIdUsuarios(), usuario.getIdUsuarios())) {
            throw new RuntimeException("Facultad no válida o no pertenece al usuario");
        }
        // Validar que el nombre no se repita en la misma facultad
        if (carreraRepository.existsByNombreCAndFacultad(newCarrera.getNombreC(), newCarrera.getFacultad())) {
            throw new RuntimeException("Ya existe una carrera con el nombre '" + newCarrera.getNombreC() +
                    "' en la facultad ID: " + newCarrera.getFacultad().getId_F());
        }
        return carreraRepository.save(newCarrera);
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

    // Actualizar carrera, solo si pertenece al usuario
    public Carrera updateCarrera(int id, Carrera updatedCarrera, Usuario usuario) {
        Carrera existing = getCarreraById(id, usuario);
        existing.setNombreC(updatedCarrera.getNombreC());
        existing.setFechaInscripcion(updatedCarrera.getFechaInscripcion());
        existing.setDuracion(updatedCarrera.getDuracion());
        existing.setPlan(updatedCarrera.getPlan());
        existing.setTituloIntermedio(updatedCarrera.getTituloIntermedio());
        existing.setFacultad(updatedCarrera.getFacultad());  // Validar que la nueva facultad pertenezca al usuario
        if (updatedCarrera.getFacultad() != null && 
            !Objects.equals(updatedCarrera.getFacultad().getUniversidad().getUsuario().getIdUsuarios(), usuario.getIdUsuarios())) {
            throw new RuntimeException("Nueva facultad no pertenece al usuario");
        }
        return carreraRepository.save(existing);
    }

    // Eliminar carrera, solo si pertenece al usuario
    @Transactional
    public void deleteCarrera(int id, Usuario usuario) {
        Carrera carrera = getCarreraById(id, usuario);
        // Lógica de eliminación en cascada
        List<Materia> materias = materiaRepository.findByCarrera(carrera);
        for (Materia materia : materias) {
            materiaRepository.deleteById(materia.getIdMateria());
        }
        carreraRepository.deleteById(id);
    }
}
