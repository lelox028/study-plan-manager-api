package com.lelox028.StudyPlanManagerApi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lelox028.StudyPlanManagerApi.Models.Facultad;
import com.lelox028.StudyPlanManagerApi.Models.Universidad;

@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Integer> {
    boolean existsByNombreFAndUniversidad(String nombreF, Universidad universidad);
}