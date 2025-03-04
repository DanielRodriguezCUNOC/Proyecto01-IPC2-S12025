package com.happycomputer.modelos;

public class InventarioComputadoraModelo {
    private Integer id;
    private Integer idEnsamblarComputadora;
    private Integer cantidad;

    public InventarioComputadoraModelo() {
    }

    public InventarioComputadoraModelo(Integer id, Integer idEnsamblarComputadora, Integer cantidad) {
        this.id = id;
        this.idEnsamblarComputadora = idEnsamblarComputadora;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEnsamblarComputadora() {
        return idEnsamblarComputadora;
    }

    public void setIdEnsamblarComputadora(Integer idEnsamblarComputadora) {
        this.idEnsamblarComputadora = idEnsamblarComputadora;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
