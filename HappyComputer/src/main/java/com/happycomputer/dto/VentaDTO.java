package com.happycomputer.dto;

import java.util.Date;

public class VentaDTO {
    private int id;
    private String usuario;
    private String idCliente;
    private double totalVenta;
    private Date fechaVenta;

    public VentaDTO(int id, String usuario, String idCliente, double totalVenta, Date fechaVenta) {
        this.id = id;
        this.usuario = usuario;
        this.idCliente = idCliente;
        this.totalVenta = totalVenta;
        this.fechaVenta = fechaVenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
}
