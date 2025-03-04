package com.happycomputer.modelos;

public class EnsamblePiezaModelo {
    private Integer id;
    private Integer idComputadora;
    private Integer idPieza;
    private Integer cantidad;

    public EnsamblePiezaModelo() {
    }

    public EnsamblePiezaModelo(Integer id, Integer idComputadora, Integer idPieza, Integer cantidad) {
        this.id = id;
        this.idComputadora = idComputadora;
        this.idPieza = idPieza;
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdComputadora() {
        return idComputadora;
    }

    public void setIdComputadora(Integer idComputadora) {
        this.idComputadora = idComputadora;
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
