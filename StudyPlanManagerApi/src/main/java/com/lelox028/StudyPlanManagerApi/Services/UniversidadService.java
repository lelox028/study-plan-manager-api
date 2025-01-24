package com.lelox028.StudyPlanManagerApi.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lelox028.StudyPlanManagerApi.Models.Carrera;
import com.lelox028.StudyPlanManagerApi.Models.Facultad;
import com.lelox028.StudyPlanManagerApi.Models.Materia;
import com.lelox028.StudyPlanManagerApi.Models.Universidad;
import com.lelox028.StudyPlanManagerApi.Repositories.CarreraRepository;
import com.lelox028.StudyPlanManagerApi.Repositories.FacultadRepository;
import com.lelox028.StudyPlanManagerApi.Repositories.MateriaRepository;
import com.lelox028.StudyPlanManagerApi.Repositories.UniversidadRepository;

@Service
public class UniversidadService {

  @Autowired
  private UniversidadRepository universidadRepository;

  @Autowired
  private FacultadRepository facultadRepository;

  @Autowired
  private CarreraRepository carreraRepository;

  @Autowired
  private MateriaRepository materiaRepository;

  public List<Universidad> getAllUniversidades() {
    return universidadRepository.findAll();
  }

  public Universidad getUniversidadById(int id) {
    Optional<Universidad> optionalUniversidad = universidadRepository.findById(id);
    if (optionalUniversidad.isPresent()) {
      return optionalUniversidad.get();
    } else {
      throw new RuntimeException("Cannot get item with ID: " + id);
    }
  }

  public Universidad createUniversidad(Universidad newUniversidad) {
    newUniversidad.setId_Universidad(0);
    if (universidadRepository.existsByNombreU(newUniversidad.getNombre_Universidad())) {
      throw new RuntimeException("Ya existe una universidad con el nombre: " + newUniversidad.getNombre_Universidad());
    }
    Universidad savedUniversidad = universidadRepository.save(newUniversidad);
    if (savedUniversidad == null) {
      throw new RuntimeException("Could not create new Universidad");
    } else {
      return savedUniversidad;
    }
  }

  public Universidad updateUniversidad(int id, Universidad updatedUniversidad) {
    Optional<Universidad> optionalUniversidad = universidadRepository.findById(id);
    if (optionalUniversidad.isPresent()) {
      Universidad oldUniversidad = optionalUniversidad.get();
      oldUniversidad.setNombre_Universidad(updatedUniversidad.getNombre_Universidad());
      return universidadRepository.save(oldUniversidad);
    } else {
      throw new RuntimeException("Item not found with ID: " + id);
    }
  }

  @Transactional
  public void deleteUniversidad(int id) {
    Optional<Universidad> optionalUniversidad = universidadRepository.findById(id);
    if (optionalUniversidad.isPresent()) {
      // Estoy seguro de que tiene que haber una mejor manera, pero mientras tanto,
      // esto funciona:
      Universidad thisUniversidad = optionalUniversidad.get();
      List<Facultad> thisFacultades = facultadRepository.findByUniversidad(thisUniversidad);
      for (Facultad facu : thisFacultades) {
        List<Carrera> thisCarreras = carreraRepository.findByFacultad(facu);
        for (Carrera carrera : thisCarreras) {
          List<Materia> thisMaterias = materiaRepository.findByCarrera(carrera);
          for (Materia materia : thisMaterias) {
            materiaRepository.deleteById(materia.getIdMateria());
          }
          carreraRepository.deleteById(carrera.getId_C());
        }
        facultadRepository.deleteById(facu.getId_F());
      }
      // fin solucion provicional
      universidadRepository.deleteById(id);
    } else {
      throw new RuntimeException("Item not found with ID: " + id);
    }
  }
}
