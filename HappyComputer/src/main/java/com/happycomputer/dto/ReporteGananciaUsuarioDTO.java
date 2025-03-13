package com.happycomputer.dto;

import java.util.List;

public class ReporteGananciaUsuarioDTO {
    private int idUsuario;
    private String nombreUsuario;
    private double totalGanancias;
    private List<ReporteGananciaDTO> ventas;

    public ReporteGananciaUsuarioDTO() {
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

    public double getTotalGanancias() {
        return totalGanancias;
    }

    public void setTotalGanancias(double totalGanancias) {
        this.totalGanancias = totalGanancias;
    }

    public List<ReporteGananciaDTO> getVentas() {
        return ventas;
    }

    public void setVentas(List<ReporteGananciaDTO> ventas) {
        this.ventas = ventas;
    }
}
