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

    @Transactional
    public List<Materia> saveAllMaterias(List<Materia> materias) {
        List<Materia> createdMaterias = new ArrayList<>();
        for (Materia materia : materias) {
            createdMaterias.add(createMateria(materia));
        }
        return createdMaterias;
    }

    // Overload para utilizar en caso de que se quiera ingresar un array de materias con una carrera diferente.
    @Transactional
    public List<Materia> saveAllMaterias(List<Materia> materias, Carrera carrera) {
        List<Materia> createdMaterias = new ArrayList<>();
        for (Materia materia : materias) {
            materia.setCarrera(carrera);
            createdMaterias.add(createMateria(materia));
        }
        return createdMaterias;
    }

    public Materia updateMateria(int id, Materia updatedMateria) { // validar cuatrimestre
        Optional<Materia> optionalMateria = materiaRepository.findById(id);
        // Recordar: cada campo no recibido sera considerado NULL y por tanto se eliminara su valor.
        if (optionalMateria.isPresent()) {
            Materia existingMateria = optionalMateria.get();
            updatedMateria.setIdMateria(id);                        // Settear el identificador al valor correcto
            Field[] fields = Materia.class.getDeclaredFields();     // Obtener todos los campos de la clase Materia
            for (Field field : fields) {
                try {
                    field.setAccessible(true);                      // Permitir acceso a campos privados
                    Object newValue = field.get(updatedMateria);    // Obtener el valor del campo correspondiente en updatedMateria
                    field.set(existingMateria, newValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();                            // Manejar posibles excepciones
                }
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
