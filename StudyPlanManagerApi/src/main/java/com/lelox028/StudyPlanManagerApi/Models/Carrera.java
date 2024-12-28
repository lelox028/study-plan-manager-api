package com.lelox028.StudyPlanManagerApi.Models;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Carreras")
public class Carrera {
    //comentario test

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_C")
    private int id_C;

    @NotBlank(message = "El nombre de la carrera no puede estar vacío")
    @Size(min = 3, max = 45, message = "El nombre debe tener entre 3 y 45 caracteres")
    @Column(name = "Nombre_C", nullable = false)
    private String nombreC; // se tuvo que quitar el guion bajo porque creaba errores en el repositorio


    @Column(name = "FechaInscripcion", nullable = false)
    private Date fechaInscripcion;

    @Column(name = "Duracion", nullable = false)
    private int duracion;

    @Column(name = "Plan", nullable = true)
    private String plan; // url al pdf del plan de estudios

    @Column(name = "TituloIntermedio", nullable = true)
    @Size(min = 3, max = 45, message = "El titulo intermedio debe tener entre 3 y 45 caracteres")
    private String tituloIntermedio;

    @ManyToOne
    @JoinColumn(name = "Dicta_Id_F", referencedColumnName = "Id_F", nullable = false)
    private Facultad facultad;

    // Getters and Setters
    public int getId_C() {
        return id_C;
    }

    public void setId_C(int id_C) {
        this.id_C = id_C;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getTituloIntermedio() {
        return tituloIntermedio;
    }

    public void setTituloIntermedio(String tituloIntermedio) {
        this.tituloIntermedio = tituloIntermedio;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    // toString
    @Override
    public String toString() {
        return "Carrera{" +
                "id_C=" + id_C +
                ", nombreC='" + nombreC + '\'' +
                ", fechaInscripcion=" + fechaInscripcion +
                ", duracion=" + duracion +
                ", plan='" + plan + '\'' +
                ", tituloIntermedio='" + tituloIntermedio + '\'' +
                ", facultad=" + facultad +
                '}';
    }
}
