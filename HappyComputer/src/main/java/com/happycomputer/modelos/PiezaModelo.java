package com.happycomputer.modelos;

public class PiezaModelo {
    private Integer id;
    private String nombre;
    private Double costo;

    public PiezaModelo() {
    }

    public PiezaModelo(Integer id, String nombre, Double costo) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }


}
