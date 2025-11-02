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
            Universidad universidad = optionalFacultad.get().getUniversidad();
            if (universidad.getUsuario().equals(usuario)) {
                return optionalFacultad.get();
            }
        }
        throw new RuntimeException("Facultad no encontrada o no pertenece al usuario");
    }

    // Obtener facultades por universidad ID, solo si la universidad pertenece al usuario
    public List<Facultad> getFacultadesbyUniversidadId(int idU, Usuario usuario) {
        Optional<Universidad> optionalUniversidad = universidadRepository.findById(idU);
        if (optionalUniversidad.isPresent() && optionalUniversidad.get().getUsuario().equals(usuario)) {
            return facultadRepository.findByUniversidad(optionalUniversidad.get());
        }
        throw new RuntimeException("Universidad no encontrada o no pertenece al usuario");
    }

    // Crear facultad, asignada a una universidad del usuario
    public Facultad createFacultad(Facultad newFacultad, Usuario usuario) {
        Universidad universidad = newFacultad.getUniversidad();
        if (universidad == null || !universidad.getUsuario().equals(usuario)) {
            throw new RuntimeException("Universidad no válida o no pertenece al usuario");
        }
        return facultadRepository.save(newFacultad);
    }

    // Actualizar facultad, solo si pertenece al usuario
    public Facultad updateFacultad(int id, Facultad updatedFacultad, Usuario usuario) {
        Facultad existing = getFacultadById(id, usuario);
        existing.setNombreF(updatedFacultad.getNombreF());
        existing.setUniversidad(updatedFacultad.getUniversidad());  // Validar que la nueva universidad pertenezca al usuario
        return facultadRepository.save(existing);
    }

    // Eliminar facultad, solo si pertenece al usuario
    public void deleteFacultad(int id, Usuario usuario) {
        Facultad facultad = getFacultadById(id, usuario);
        facultadRepository.deleteById(id);
    }
}
