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
import com.lelox028.StudyPlanManagerApi.Models.Usuario;
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

  // Obtener todas las universidades del usuario
  public List<Universidad> getAllUniversidades(Usuario usuario) {
    return universidadRepository.findByUsuario(usuario);
  }

  // Obtener universidad por ID, solo si pertenece al usuario
  public Universidad getUniversidadById(int id, Usuario usuario) {
    Optional<Universidad> optionalUniversidad = universidadRepository.findById(id);
    if (optionalUniversidad.isPresent() && optionalUniversidad.get().getUsuario().equals(usuario)) {
      return optionalUniversidad.get();
    } else {
      throw new RuntimeException("Universidad no encontrada o no pertenece al usuario");
    }
  }

  public Universidad createUniversidad(Universidad newUniversidad, Usuario usuario) {
    newUniversidad.setUsuario(usuario);
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

  // Actualizar universidad, solo si pertenece al usuario
  public Universidad updateUniversidad(int id, Universidad updatedUniversidad, Usuario usuario) {
    Universidad existing = getUniversidadById(id, usuario);
    existing.setNombre_Universidad(updatedUniversidad.getNombre_Universidad());
    return universidadRepository.save(existing);
  }

  // Eliminar universidad, solo si pertenece al usuario
  @Transactional
  public void deleteUniversidad(int id, Usuario usuario) {
    Universidad universidad = getUniversidadById(id, usuario);
    // Lógica de eliminación en cascada (igual que antes, pero filtrada)
    List<Facultad> facultades = facultadRepository.findByUniversidad(universidad);
    for (Facultad facu : facultades) {
      List<Carrera> carreras = carreraRepository.findByFacultad(facu);
      for (Carrera carrera : carreras) {
        List<Materia> materias = materiaRepository.findByCarrera(carrera);
        for (Materia materia : materias) {
          materiaRepository.deleteById(materia.getIdMateria());
        }
        carreraRepository.deleteById(carrera.getId_C());
      }
      facultadRepository.deleteById(facu.getId_F());
    }
    universidadRepository.deleteById(id);
  }
}
