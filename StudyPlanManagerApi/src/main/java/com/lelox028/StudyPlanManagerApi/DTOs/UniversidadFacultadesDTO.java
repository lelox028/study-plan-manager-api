package com.lelox028.StudyPlanManagerApi.DTOs;

import java.util.List;

import com.lelox028.StudyPlanManagerApi.Models.Universidad;

public class UniversidadFacultadesDTO {
    private int id_Universidad;
    private String nombre_Universidad;
    private List<FacultadCarrerasDTO> facultades;

    public UniversidadFacultadesDTO(Universidad newUniversidad, List<FacultadCarrerasDTO> newFacultades) {
        this.id_Universidad = newUniversidad.getId_Universidad();
        this.nombre_Universidad = newUniversidad.getNombre_Universidad();
        this.facultades = newFacultades;
    }

    public int getId_Universidad() {
        return id_Universidad;
    }

    public void setId_Universidad(int id_Universidad) {
        this.id_Universidad = id_Universidad;
    }

    public String getNombre_Universidad() {
        return nombre_Universidad;
    }

    public void setNombre_Universidad(String nombre_Universidad) {
        this.nombre_Universidad = nombre_Universidad;
    }

    public List<FacultadCarrerasDTO> getFacultades() {
        return facultades;
    }

    public void setFacultades(List<FacultadCarrerasDTO> facultades) {
        this.facultades = facultades;
    }
}
