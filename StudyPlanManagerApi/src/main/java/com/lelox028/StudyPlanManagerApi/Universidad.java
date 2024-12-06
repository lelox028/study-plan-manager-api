package com.lelox028.StudyPlanManagerApi;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Universidades")
public class Universidad {

    @Id
    private int id_U;

    private String nombre_U;

    // Getters y Setters
    public int getId_Universidad() {
        return id_U;
    }

    public String getNombre_Universidad() {
        return nombre_U;
    }

    public void setId_Universidad(int newId) {
        this.id_U = newId;
    }

    public void setNombre_Universidad(String newNombre) {
        this.nombre_U = newNombre;
    }
}
