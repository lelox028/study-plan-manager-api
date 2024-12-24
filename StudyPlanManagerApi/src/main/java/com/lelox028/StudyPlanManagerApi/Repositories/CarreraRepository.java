package com.lelox028.StudyPlanManagerApi.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lelox028.StudyPlanManagerApi.Models.Carrera;
import com.lelox028.StudyPlanManagerApi.Models.Facultad;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {
    boolean existsByNombreCAndFacultad(String nombreC, Facultad facultad);
    List<Carrera> findByFacultad(Facultad facultad);
}