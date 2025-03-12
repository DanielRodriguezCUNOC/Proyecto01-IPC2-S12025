package com.happycomputer.dto;

import java.util.Date;

public class ReporteVentaDTO {
    private int idVenta;
    private String nombreCliente;
    private Date fechaVenta;
    private String nombreComputadora;
    private double precioUnitario;
    private int cantidad;
    private double totalVenta;

    public ReporteVentaDTO(int idVenta, String nombreCliente, Date fechaVenta, String nombreComputadora, double precioUnitario, int cantidad, double totalVenta) {
        this.idVenta = idVenta;
        this.nombreCliente = nombreCliente;
        this.fechaVenta = fechaVenta;
        this.nombreComputadora = nombreComputadora;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.totalVenta = totalVenta;
    }

    public ReporteVentaDTO() {
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getNombreComputadora() {
        return nombreComputadora;
    }

    public void setNombreComputadora(String nombreComputadora) {
        this.nombreComputadora = nombreComputadora;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }
}
