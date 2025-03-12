package com.happycomputer.modelos;

import java.util.Date;

public class DevolucionModelo {
    private Integer id;
    private Integer idVenta;
    private String fechaDevolucion;
    private double perdida;

    public DevolucionModelo() {
    }

    public DevolucionModelo(Integer id, Integer idVenta, String fechaDevolucion, double perdida) {
        this.id = id;
        this.idVenta = idVenta;
        this.fechaDevolucion = fechaDevolucion;
        this.perdida = perdida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public double getPerdida() {
        return perdida;
    }

    public void setPerdida(double perdida) {
        this.perdida = perdida;
    }
}
