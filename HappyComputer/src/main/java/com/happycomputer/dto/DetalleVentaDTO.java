package com.happycomputer.dto;

public class DetalleVentaDTO {
    private int id;
    private int idVenta;
    private int idComputadora;
    private double precioUnidad;
    private String nombreComputadora;

    public DetalleVentaDTO(int id, int idVenta, int idComputadora, double precioUnidad, String nombreComputadora) {
        this.id = id;
        this.idVenta = idVenta;
        this.idComputadora = idComputadora;
        this.precioUnidad = precioUnidad;
        this.nombreComputadora = nombreComputadora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdComputadora() {
        return idComputadora;
    }

    public void setIdComputadora(int idComputadora) {
        this.idComputadora = idComputadora;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public String getNombreComputadora() {
        return nombreComputadora;
    }

    public void setNombreComputadora(String nombreComputadora) {
        this.nombreComputadora = nombreComputadora;
    }
}
