package com.happycomputer.persistenciadatos;

import com.happycomputer.modelos.DetalleVentaModelo;

import java.sql.SQLException;
import java.util.List;

public class DetalleVentaDAO extends CrudDAO<DetalleVentaModelo> {
    @Override
    public DetalleVentaModelo insert(DetalleVentaModelo entity) throws SQLException {
        return null;
    }

    @Override
    public void update(DetalleVentaModelo entity) throws SQLException {

    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public DetalleVentaModelo findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<DetalleVentaModelo> findAll() throws SQLException {
        return null;
    }
}
