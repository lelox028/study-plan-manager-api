package com.lelox028.StudyPlanManagerApi.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lelox028.StudyPlanManagerApi.Models.Universidad;

@Repository
public interface UniversidadRepository extends JpaRepository<Universidad, Integer> {
    boolean existsByNombreU(String nombreU);
    Optional<Universidad> findByNombreU(String nombreU);
}