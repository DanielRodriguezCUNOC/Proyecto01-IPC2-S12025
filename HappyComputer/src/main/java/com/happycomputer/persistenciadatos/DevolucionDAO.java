package com.happycomputer.persistenciadatos;

import com.happycomputer.modelos.DevolucionModelo;
import com.happycomputer.util.ConectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DevolucionDAO extends CrudDAO<DevolucionModelo> {
    @Override
    public DevolucionModelo insert(DevolucionModelo entity) throws SQLException {
        String sql = "INSERT INTO Devolucion (id_venta, fecha_devolucion, perdida) VALUES (?, ?, ?)";
        try (Connection con = ConectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            //Convertir la fecha de devolución a un formato de fecha de SQL
            java.sql.Date fechaDevolucion = java.sql.Date.valueOf(entity.getFechaDevolucion());
            ps.setInt(1, entity.getIdVenta());
            ps.setDate(2, fechaDevolucion);
            ps.setDouble(3, entity.getPerdida());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getInt(1));
                    }
                }
            }
            return entity;

        }
    }

    @Override
    public void update(DevolucionModelo entity) throws SQLException {

    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public DevolucionModelo findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<DevolucionModelo> findAll() throws SQLException {
        return null;
    }

    // Obtener la lista de devoluciones por cliente y rango de fechas
    public List<DevolucionModelo> findByCostumer(String nit, String fechaInicio, String fechaFin) throws SQLException {
        String sql = "SELECT * FROM Devolucion WHERE id_venta IN (SELECT id FROM Venta WHERE id_cliente = ? AND fecha BETWEEN ? AND ?)";
        try (Connection con = ConectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            java.sql.Date fechaDevolucion = java.sql.Date.valueOf(fechaInicio);
            java.sql.Date fechaFinal = java.sql.Date.valueOf(fechaFin);
            ps.setString(1, nit);
            ps.setDate(2, fechaDevolucion);
            ps.setDate(3, fechaFinal);
            try (ResultSet rs = ps.executeQuery()) {
                List<DevolucionModelo> devoluciones = new ArrayList<>();
                while (rs.next()) {
                    DevolucionModelo devolucion = new DevolucionModelo();
                    devolucion.setId(rs.getInt("id"));
                    devolucion.setIdVenta(rs.getInt("id_venta"));
                    devolucion.setFechaDevolucion(rs.getDate("fecha_devolucion").toString());
                    devolucion.setPerdida(rs.getDouble("perdida"));
                    devoluciones.add(devolucion);
                }
                return devoluciones;
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener las devoluciones", e);
        }
    }
}
