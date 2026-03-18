package com.lelox028.StudyPlanManagerApi.DTOs;
import java.util.List;
import com.lelox028.StudyPlanManagerApi.Models.Carrera;
import com.lelox028.StudyPlanManagerApi.Models.Facultad;


public class FacultadCarrerasDTO {
    private int id_F;
    private String nombre_F;
    private List<Carrera> carreras;

    public FacultadCarrerasDTO(Facultad newFacultad, List<Carrera> newCarreras) {
        this.id_F = newFacultad.getId_F();
        this.nombre_F = newFacultad.getNombreF();
        this.carreras = newCarreras;
    }

    public int getId_F() {
        return id_F;
    }

    public void setId_F(int id_F) {
        this.id_F = id_F;
    }

    public String getNombre_F() {
        return nombre_F;
    }

    public void setNombre_F(String nombre_F) {
        this.nombre_F = nombre_F;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }
}
