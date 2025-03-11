package com.happycomputer.modelos;

public class DetalleVentaModelo {
    private Integer id;
    private Integer idVenta;
    private Integer idComputadora;
    private Double precioUnidad;


    public DetalleVentaModelo(Integer id, Integer idVenta, Integer idComputadora, Double precioUnidad) {
        this.id = id;
        this.idVenta = idVenta;
        this.idComputadora = idComputadora;
        this.precioUnidad = precioUnidad;
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

    public Integer getIdComputadora() {
        return idComputadora;
    }

    public void setIdComputadora(Integer idComputadora) {
        this.idComputadora = idComputadora;
    }

    public Double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(Double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }
}
