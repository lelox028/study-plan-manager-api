package com.lelox028.StudyPlanManagerApi.Models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Materias")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_M")
    private int idM;

    @Column(name = "Nombre_M", nullable = false, length = 45)
    private String nombreM;

    @Column(name = "Anio", nullable = false)
    private int anio;

    @Column(name = "Cuatrimestre", nullable = false, length = 45)
    @Enumerated(EnumType.STRING)
    private Cuatrimestre cuatrimestre;

    @Column(name = "Estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoMateria estado;

    @Column(name = "FechaAprobacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAprobacion;

    @Column(name = "Calificacion")
    private Float calificacion;

    @Column(name = "FechaRegularizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaRegularizacion;

    @Column(name = "RequeridaPorTituloIntermedio", nullable = false)
    private boolean requeridaPorTituloIntermedio;

    @ManyToOne
    @JoinColumn(name = "Corresponden_Id_C", nullable = false)
    private Carrera carrera;

    @ManyToOne
    @JoinColumn(name = "Necesitan_Id_M", nullable = false)
    private Materia materiaRequisito;

    // Getters y Setters
    public int getIdMateria() {
        return idM;
    }

    public void setIdMateria(int idMateria) {
        this.idM = idMateria;
    }

    public String getNombreMateria() {
        return nombreM;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreM = nombreMateria;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Cuatrimestre getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(Cuatrimestre cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public EstadoMateria getEstado() {
        return estado;
    }

    public void setEstado(EstadoMateria estado) {
        this.estado = estado;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Float calificacion) {
        this.calificacion = calificacion;
    }

    public Date getFechaRegularizacion() {
        return fechaRegularizacion;
    }

    public void setFechaRegularizacion(Date fechaRegularizacion) {
        this.fechaRegularizacion = fechaRegularizacion;
    }

    public boolean isRequeridaPorTituloIntermedio() {
        return requeridaPorTituloIntermedio;
    }

    public void setRequeridaPorTituloIntermedio(boolean requeridaPorTituloIntermedio) {
        this.requeridaPorTituloIntermedio = requeridaPorTituloIntermedio;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Materia getMateriaRequisito() {
        return materiaRequisito;
    }

    public void setMateriaRequisito(Materia materiaRequisito) {
        this.materiaRequisito = materiaRequisito;
    }

    // Enum para el campo Estado
    public enum EstadoMateria {
        _0, _1, _2, _3, _4;
    }

    // Enum para el campo Cuatrimestre
    public enum Cuatrimestre {
        _1, _2, Anual;
    }
}
