package com.happycomputer.persistenciadatos;

import com.happycomputer.modelos.PiezaModelo;

import java.sql.SQLException;
import java.util.List;

public class PiezaDAO extends CrudDAO<PiezaModelo> {

    @Override
    public void insert(PiezaModelo entity) {
        System.out.println("Insertando pieza");
    }

    @Override
    public void update(PiezaModelo entity) {
        System.out.println("Actualizando pieza");
    }

    @Override
    public void delete(PiezaModelo object) {
        System.out.println("Eliminando pieza");
    }

    @Override
    public PiezaModelo findById(Integer id) {
        System.out.println("Buscando pieza por id");
        return new PiezaModelo();
    }

    @Override
    public List<PiezaModelo> findAll() throws SQLException {
        return List.of();
    }

}
