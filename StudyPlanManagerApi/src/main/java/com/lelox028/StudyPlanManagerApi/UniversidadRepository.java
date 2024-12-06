package com.lelox028.StudyPlanManagerApi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversidadRepository extends JpaRepository<Universidad, Integer> {
    boolean existsByNombreU(String nombreU);
}