package com.lelox028.StudyPlanManagerApi.Services;

import com.lelox028.StudyPlanManagerApi.Models.Facultad;
import com.lelox028.StudyPlanManagerApi.Models.Universidad;
import com.lelox028.StudyPlanManagerApi.Models.Usuario;
import com.lelox028.StudyPlanManagerApi.Repositories.FacultadRepository;
import com.lelox028.StudyPlanManagerApi.Repositories.UniversidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Objects;  // Agrega si no está

@Service
public class FacultadService {

    @Autowired
    private FacultadRepository facultadRepository;

    @Autowired
    private UniversidadRepository universidadRepository;

    // Obtener todas las facultades del usuario (a través de sus universidades)
    public List<Facultad> getAllFacultades(Usuario usuario) {
        List<Universidad> universidades = universidadRepository.findByUsuario(usuario);
        return facultadRepository.findByUniversidadIn(universidades);  // Necesitas este método en FacultadRepository
    }

    // Obtener facultad por ID, solo si pertenece a una universidad del usuario
    public Facultad getFacultadById(int id, Usuario usuario) {
        Optional<Facultad> optionalFacultad = facultadRepository.findById(id);
        if (optionalFacultad.isPresent()) {
            Facultad facultad = optionalFacultad.get();
            Universidad universidad = facultad.getUniversidad();
            if (Objects.equals(universidad.getUsuario().getIdUsuarios(), usuario.getIdUsuarios())) {
                return facultad;
            }
        }
        throw new RuntimeException("Facultad no encontrada o no pertenece al usuario");
    }

    // Obtener facultades por universidad ID, solo si la universidad pertenece al usuario
    public List<Facultad> getFacultadesbyUniversidadId(int idU, Usuario usuario) {
        Optional<Universidad> optionalUniversidad = universidadRepository.findById(idU);
        if (optionalUniversidad.isPresent()) {
            Universidad universidad = optionalUniversidad.get();
            // Compara por ID en lugar de equals()
            if (Objects.equals(universidad.getUsuario().getIdUsuarios(), usuario.getIdUsuarios())) {
                return facultadRepository.findByUniversidad(universidad);
            }
        }
        throw new RuntimeException("Universidad no encontrada o no pertenece al usuario");
    }

    // Crear facultad, asignada a una universidad del usuario
    public Facultad createFacultad(Facultad newFacultad, Usuario usuario) {
        Universidad universidad = newFacultad.getUniversidad();
        if (universidad == null || !Objects.equals(universidad.getUsuario().getIdUsuarios(), usuario.getIdUsuarios())) {
            throw new RuntimeException("Universidad no válida o no pertenece al usuario");
        }
        return facultadRepository.save(newFacultad);
    }

    // Actualizar facultad, solo si pertenece al usuario
    public Facultad updateFacultad(int id, Facultad updatedFacultad, Usuario usuario) {
        Facultad existing = getFacultadById(id, usuario);
        existing.setNombreF(updatedFacultad.getNombreF());
        existing.setUniversidad(updatedFacultad.getUniversidad());
        // Valida que la nueva universidad pertenezca al usuario
        if (updatedFacultad.getUniversidad() != null && 
            !Objects.equals(updatedFacultad.getUniversidad().getUsuario().getIdUsuarios(), usuario.getIdUsuarios())) {
            throw new RuntimeException("Nueva universidad no pertenece al usuario");
        }
        return facultadRepository.save(existing);
    }

    // Eliminar facultad, solo si pertenece al usuario
    public void deleteFacultad(int id, Usuario usuario) {
        Facultad facultad = getFacultadById(id, usuario);
        facultadRepository.deleteById(id);
    }
}
