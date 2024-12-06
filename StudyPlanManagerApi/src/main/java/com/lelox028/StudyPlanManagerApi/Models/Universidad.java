package com.lelox028.StudyPlanManagerApi.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Universidades")
public class Universidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_U;

    @NotBlank(message = "El nombre de la universidad no puede estar vacío")
    @Size(min = 3, max = 45, message = "El nombre debe tener entre 3 y 45 caracteres")
    @Column(name = "Nombre_U", nullable = false, unique = true)
    private String nombreU; //se tuvo que quitar el guion bajo porque creaba errores en el repositorio

    // Getters y Setters
    public int getId_Universidad() {
        return id_U;
    }

    public String getNombre_Universidad() {
        return nombreU;
    }

    public void setId_Universidad(int newId) {
        this.id_U = newId;
    }

    public void setNombre_Universidad(String newNombre) {
        this.nombreU = newNombre;
    }
}
