package com.lelox028.StudyPlanManagerApi.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lelox028.StudyPlanManagerApi.Models.Carrera;
import com.lelox028.StudyPlanManagerApi.Models.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer> {

    boolean existsByNombreMAndCarrera(String nombreM, Carrera carrera);

    List<Materia> findByCarrera(Carrera carrera);

    @Query(value = "SELECT * FROM Materias WHERE Corresponden_Id_C = :idC AND (Estado = 'Aprobado' OR Estado = 'Promocionado')", nativeQuery = true)
    List<Materia> getApprovedByCarrera(@Param("idC") int idC);

    Optional<Materia> findByNombreMAndCarrera(String nombreM, Carrera carrera);

}