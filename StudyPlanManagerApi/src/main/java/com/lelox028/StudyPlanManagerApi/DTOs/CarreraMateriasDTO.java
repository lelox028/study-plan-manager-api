package com.lelox028.StudyPlanManagerApi.DTOs;

import java.util.List;

import com.lelox028.StudyPlanManagerApi.Models.Carrera;
import com.lelox028.StudyPlanManagerApi.Models.Materia;

public class CarreraMateriasDTO {
    private Carrera carrera;
    private List<Materia> materias;

    public CarreraMateriasDTO(Carrera newCarrera, List<Materia> newMaterias) {
        this.carrera = newCarrera;
        this.materias = newMaterias;
    }

    // Getters y Setters
    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }
}
