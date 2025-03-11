package com.happycomputer.modelos;

public class ClienteModelo {
    private Integer id;
    private String nombre;
    private String nit;
    private String direccion;
    private String telefono;
    private String correo;

    public ClienteModelo() {
    }

    public ClienteModelo(String nit, String nombre, String direccion) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
