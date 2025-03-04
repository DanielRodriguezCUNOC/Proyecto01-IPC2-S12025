package com.happycomputer.modelos;

public class InventarioPiezaModelo {
    private Integer id;
    private Integer idPieza;
    private Integer cantidad;

    public InventarioPiezaModelo() {
    }

    public InventarioPiezaModelo(Integer id, Integer idPieza, Integer cantidad) {
        this.id = id;
        this.idPieza = idPieza;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(Integer idPieza) {
        this.idPieza = idPieza;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
