package com.lelox028.StudyPlanManagerApi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lelox028.StudyPlanManagerApi.Models.Carrera;
import com.lelox028.StudyPlanManagerApi.Models.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer> {
    boolean existsByNombreMAndCarrera(String nombreM, Carrera carrera);
}