package com.happycomputer.modelos;

import java.util.Date;

public class EnsamblarComputadoraModelo {
    private Integer id;
    private Integer idComputadora;
    private Integer idUsuario;
    private Double costoEnsamble;
    private Date fechaEnsamble;

    public EnsamblarComputadoraModelo() {
    }

    public EnsamblarComputadoraModelo(Integer id, Integer idComputadora, Integer idUsuario, Date fechaEnsamble, Double costoEnsamble) {
        this.id = id;
        this.idComputadora = idComputadora;
        this.idUsuario = idUsuario;
        this.costoEnsamble = costoEnsamble;
        this.fechaEnsamble = fechaEnsamble;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdComputadora() {
        return idComputadora;
    }

    public void setIdComputadora(Integer idComputadora) {
        this.idComputadora = idComputadora;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getCostoEnsamble() {
        return costoEnsamble;
    }

    public void setCosto_ensamble(Double costoEnsamble) {
        this.costoEnsamble = costoEnsamble;
    }

    public Date getFechaEnsamble() {
        return fechaEnsamble;
    }

    public void setFechaEnsamble(Date fechaEnsamble) {
        this.fechaEnsamble = fechaEnsamble;
    }
}
