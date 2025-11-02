package com.lelox028.StudyPlanManagerApi.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lelox028.StudyPlanManagerApi.Models.Facultad;
import com.lelox028.StudyPlanManagerApi.Models.Universidad;

@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Integer> {
    List<Facultad> findByUniversidad(Universidad universidad);
    List<Facultad> findByUniversidadIn(List<Universidad> universidades);
    Optional<Facultad> findByNombreFAndUniversidad(String nombreF, Universidad universidad);
}