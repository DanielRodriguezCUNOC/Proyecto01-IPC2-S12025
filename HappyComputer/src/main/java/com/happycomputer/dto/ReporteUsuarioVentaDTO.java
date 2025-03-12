package com.happycomputer.dto;

import java.util.List;

public class ReporteUsuarioVentaDTO {
    private int idUsuario;
    private String nombreUsuario;
    private int totalVentas;
    private List<ReporteVentaDTO> ventas;

    public ReporteUsuarioVentaDTO() {
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(int totalVentas) {
        this.totalVentas = totalVentas;
    }

    public List<ReporteVentaDTO> getVentas() {
        return ventas;
    }

    public void setVentas(List<ReporteVentaDTO> ventas) {
        this.ventas = ventas;
    }
}
