package com.happycomputer.dto;

import java.util.List;

public class ReporteComputadoraMasVendidaDTO {
    private int idComputadora;
    private String nombreComputadora;
    private int totalVentas;
    private List<ReporteVentaDTO> ventas;

    public ReporteComputadoraMasVendidaDTO() {
    }

    public int getIdComputadora() {
        return idComputadora;
    }

    public void setIdComputadora(int idComputadora) {
        this.idComputadora = idComputadora;
    }

    public String getNombreComputadora() {
        return nombreComputadora;
    }

    public void setNombreComputadora(String nombreComputadora) {
        this.nombreComputadora = nombreComputadora;
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
