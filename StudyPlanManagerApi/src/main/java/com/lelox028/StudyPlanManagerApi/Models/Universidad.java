package com.lelox028.StudyPlanManagerApi.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;  // Agrega este import

@Entity
@Table(name = "Universidades")
public class Universidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_U;

    @NotBlank(message = "El nombre de la universidad no puede estar vacío")
    @Size(min = 3, max = 45, message = "El nombre debe tener entre 3 y 45 caracteres")
    @Column(name = "Nombre_U", nullable = false, unique = true) //REVISAR!! este campo no es unique!!
    private String nombreU;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuarios_idUsuarios", nullable = false)
    @JsonIgnore  // Evita serializar el usuario en JSON
    private Usuario usuario;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Universidad [id= " + id_U + ", nombre=" + nombreU + "]";
    }
}
