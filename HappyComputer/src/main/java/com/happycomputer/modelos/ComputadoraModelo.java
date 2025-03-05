package com.happycomputer.modelos;

public class ComputadoraModelo {
    private Integer id;
    private String nombre;
    private Double precio_venta;
    private Boolean estado;

    public ComputadoraModelo() {
    }

    public ComputadoraModelo(Integer id, String nombre, Double precio_venta) {
        this.id = id;
        this.nombre = nombre;
        this.precio_venta = precio_venta;
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

    public Double getPrecioVenta() {
        return precio_venta;
    }

    public void setPrecioVenta(Double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
