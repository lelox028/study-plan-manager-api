package com.lelox028.StudyPlanManagerApi.Services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lelox028.StudyPlanManagerApi.Models.Materia;
import com.lelox028.StudyPlanManagerApi.Models.Carrera;
import com.lelox028.StudyPlanManagerApi.Models.Facultad;
import com.lelox028.StudyPlanManagerApi.Models.Universidad;
import com.lelox028.StudyPlanManagerApi.Models.Usuario;
import com.lelox028.StudyPlanManagerApi.Repositories.MateriaRepository;
import com.lelox028.StudyPlanManagerApi.Repositories.CarreraRepository;
import com.lelox028.StudyPlanManagerApi.Repositories.FacultadRepository;
import com.lelox028.StudyPlanManagerApi.Repositories.UniversidadRepository;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private FacultadRepository facultadRepository;

    @Autowired
    private UniversidadRepository universidadRepository;

    // Obtener todas las materias del usuario (a través de sus carreras)
    public List<Materia> getAllMaterias(Usuario usuario) {
        List<Universidad> universidades = universidadRepository.findByUsuario(usuario);
        List<Facultad> facultades = facultadRepository.findByUniversidadIn(universidades);
        List<Carrera> carreras = carreraRepository.findByFacultadIn(facultades);
        return materiaRepository.findByCarreraIn(carreras);
    }

    // Obtener materia por ID, solo si pertenece a una carrera del usuario
    public Materia getMateriaById(int id, Usuario usuario) {
        Optional<Materia> optionalMateria = materiaRepository.findById(id);
        if (optionalMateria.isPresent()) {
            Carrera carrera = optionalMateria.get().getCarrera();
            if (carrera.getFacultad().getUniversidad().getUsuario().equals(usuario)) {
                return optionalMateria.get();
            }
        }
        throw new RuntimeException("Materia no encontrada o no pertenece al usuario");
    }

    // Obtener materias por carrera ID, solo si la carrera pertenece al usuario
    public List<Materia> getMateriasByCarreraId(int idC, Usuario usuario) {
        Optional<Carrera> optionalCarrera = carreraRepository.findById(idC);
        if (optionalCarrera.isPresent() && optionalCarrera.get().getFacultad().getUniversidad().getUsuario().equals(usuario)) {
            Carrera carrera = optionalCarrera.get();
            return materiaRepository.findByCarrera(carrera);
        }
        throw new RuntimeException("Carrera no encontrada o no pertenece al usuario");
    }

    // Get Approved by Carrera, solo si pertenece al usuario
    public List<Materia> getApprovedByCarreraId(int idC, Usuario usuario) {
        Optional<Carrera> optionalCarrera = carreraRepository.findById(idC);
        if (optionalCarrera.isPresent() && optionalCarrera.get().getFacultad().getUniversidad().getUsuario().equals(usuario)) {
            return materiaRepository.getApprovedByCarrera(idC);
        }
        throw new RuntimeException("Carrera no encontrada o no pertenece al usuario");
    }

    // Crear materia, solo si la carrera pertenece al usuario
    public Materia createMateria(Materia newMateria, Usuario usuario) {
        // Validar que la Carrera exista y pertenezca al usuario
        Optional<Carrera> optionalCarrera = carreraRepository.findById(newMateria.getCarrera().getId_C());
        if (!optionalCarrera.isPresent() || !optionalCarrera.get().getFacultad().getUniversidad().getUsuario().equals(usuario)) {
            throw new RuntimeException("Carrera no encontrada o no pertenece al usuario");
        }

        // Lógica existente intacta
        newMateria.setIdMateria(0);
        Carrera tempCarrera = optionalCarrera.get();
        newMateria.setCarrera(tempCarrera);

        if (materiaRepository.existsByNombreMAndCarrera(newMateria.getNombreMateria(), newMateria.getCarrera())) {
            throw new RuntimeException("Ya existe una Materia con el nombre '" + newMateria.getNombreMateria() +
                    "' en la Carrera con ID: " + newMateria.getCarrera().getId_C());
        }

        Materia savedMateria = materiaRepository.save(newMateria);
        if (savedMateria == null) {
            throw new RuntimeException("No se pudo crear la nueva Materia");
        } else {
            return savedMateria;
        }
    }

    // Batch create, validar cada materia
    @Transactional
    public List<Materia> saveAllMaterias(List<Materia> materias, Usuario usuario) {
        List<Materia> savedMaterias = new ArrayList<>();
        for (Materia materia : materias) {
            // Validar carrera para cada materia
            Optional<Carrera> optionalCarrera = carreraRepository.findById(materia.getCarrera().getId_C());
            if (!optionalCarrera.isPresent() || !optionalCarrera.get().getFacultad().getUniversidad().getUsuario().equals(usuario)) {
                throw new RuntimeException("Carrera de materia no pertenece al usuario");
            }
            // Lógica existente
            materia.setCarrera(optionalCarrera.get());
            savedMaterias.add(materiaRepository.save(materia));
        }
        return savedMaterias;
    }

    // Overload para batch con carrera específica (validar carrera)
    @Transactional
    public List<Materia> saveAllMaterias(List<Materia> materias, Carrera carrera, Usuario usuario) {
        if (!carrera.getFacultad().getUniversidad().getUsuario().equals(usuario)) {
            throw new RuntimeException("Carrera no pertenece al usuario");
        }
        // Lógica existente intacta
        for (Materia materia : materias) {
            materia.setCarrera(carrera);
        }
        return materiaRepository.saveAll(materias);
    }

    // Actualizar materia, solo si pertenece al usuario
    public Materia updateMateria(int id, Materia updatedMateria, Usuario usuario) {
        Materia existing = getMateriaById(id, usuario);  // Valida propiedad
        // Lógica existente intacta (reflection, etc.)
        Optional<Materia> optionalMateria = materiaRepository.findById(id);
        if (optionalMateria.isPresent()) {
            Materia existingMateria = optionalMateria.get();
            updatedMateria.setIdMateria(id);
            Field[] fields = Materia.class.getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    Object newValue = field.get(updatedMateria);
                    field.set(existingMateria, newValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return materiaRepository.save(existingMateria);
        } else {
            throw new RuntimeException("No se encontró una Materia con el ID: " + id);
        }
    }

    // Eliminar materia, solo si pertenece al usuario
    public void deleteMateria(int id, Usuario usuario) {
        Materia materia = getMateriaById(id, usuario);  // Valida propiedad
        materiaRepository.deleteById(materia.getIdMateria());
    }
}
