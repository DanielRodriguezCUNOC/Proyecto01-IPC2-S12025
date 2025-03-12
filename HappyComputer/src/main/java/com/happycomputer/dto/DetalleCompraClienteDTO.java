package com.happycomputer.dto;

import java.util.Date;

public class DetalleCompraClienteDTO {
    private Integer id;
    private String idCliente;
    private String usuario;
    private Double totalVenta;
    private Date fechaVenta;

    public DetalleCompraClienteDTO(Integer id, String idCliente, Double totalVenta, Date fechaVenta, String usuario) {
        this.id = id;
        this.idCliente = idCliente;
        this.totalVenta = totalVenta;
        this.fechaVenta = fechaVenta;
        this.usuario = usuario;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
