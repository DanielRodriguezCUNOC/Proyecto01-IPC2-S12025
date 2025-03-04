package com.happycomputer.modelos;

public class UsuarioModelo {
    private Integer id;
    private String usuario;
    private String password;
    private Integer idRol;
    private Boolean estado;

    public UsuarioModelo() {
    }
    public UsuarioModelo(Integer id, String usuario, String password, Integer idRol, Boolean estado) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        this.idRol = idRol;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
