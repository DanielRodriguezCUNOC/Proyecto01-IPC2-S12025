package com.happycomputer.modelos;

import java.util.Date;

public class VentaModelo {
    private Integer id;
    private String idCliente;
    private Integer idUsuario;
    private Double totalVenta;
    private Date fechaVenta;

    public VentaModelo() {
    }

    public VentaModelo(Integer id, Integer idUsuario, String idCliente, Double totalVenta, Date fechaVenta) {
        this.id = id;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.totalVenta = totalVenta;
        this.fechaVenta = fechaVenta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }
}
