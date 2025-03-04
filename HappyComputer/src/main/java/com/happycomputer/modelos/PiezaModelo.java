package com.happycomputer.modelos;

public class PiezaModelo {
    private Integer id;
    private String nombre;
    private Integer costo;

    public PiezaModelo() {
    }

    public PiezaModelo(Integer id, String nombre, Integer costo) {
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

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }


}
