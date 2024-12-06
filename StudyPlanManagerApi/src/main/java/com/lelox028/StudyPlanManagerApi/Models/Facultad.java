package com.lelox028.StudyPlanManagerApi.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Facultades")
public class Facultad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_F")
    private int id_F;

    @NotBlank(message = "El nombre de la facultad no puede estar vacío")
    @Size(min = 3, max = 45, message = "El nombre debe tener entre 3 y 45 caracteres")
    @Column(name = "Nombre_F", nullable = false)
    private String nombreF;

    @ManyToOne
    @JoinColumn(name = "Pertenecen_Id_U", referencedColumnName = "Id_U", nullable = false)
    private Universidad universidad;

    // Getters y setters

    public int getId_F() {
        return id_F;
    }

    public void setId_F(int id_F) {
        this.id_F = id_F;
    }

    public String getNombreF() {
        return nombreF;
    }

    public void setNombreF(String nombre_F) {
        this.nombreF = nombre_F;
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }

    @Override
    public String toString() {
        return "Facultad [id= " + id_F + ", nombre=" + nombreF + ", universidad=" + universidad.toString() + "]";
    }
}
