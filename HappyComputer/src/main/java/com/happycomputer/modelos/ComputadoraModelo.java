package com.happycomputer.modelos;

public class ComputadoraModelo {
    private Integer id;
    private String nombre;
    private Integer precio_venta;
    private Boolean estado;

    public ComputadoraModelo() {
    }

    public ComputadoraModelo(Integer id, String nombre, Integer precio_venta, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.precio_venta = precio_venta;
        this.estado = estado;
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

    public Integer getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Integer precio_venta) {
        this.precio_venta = precio_venta;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
