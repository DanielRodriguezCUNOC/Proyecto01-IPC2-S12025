package com.happycomputer.persistenciadatos;

import com.happycomputer.modelos.ComputadoraModelo;
import com.happycomputer.modelos.VentaModelo;
import com.happycomputer.util.ConectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO extends CrudDAO<VentaModelo> {

    @Override
    public VentaModelo insert(VentaModelo entity) throws SQLException {
        String sql = "INSERT INTO Venta (id_usuario, id_cliente, total, fecha) VALUES (?, ?, ?, ?)";
        try (Connection connect = ConectDB.getConnection(); PreparedStatement ps = connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, entity.getIdUsuario());
            ps.setString(2, entity.getIdCliente());
            ps.setDouble(3, entity.getTotalVenta());
            ps.setDate(4, new java.sql.Date(entity.getFechaVenta().getTime()));
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys();) {
                    if (generatedKeys.next()) {
                        //* Aquí se asigna el id generado a la entidad
                        entity.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }
        return entity;
    }

    @Override
    public void update(VentaModelo entity) throws SQLException {

    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public VentaModelo findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<VentaModelo> findAll() throws SQLException {
        return null;
    }

    //Obtener la computadora por su id
    public ComputadoraModelo findComputadoraById(Integer id) throws SQLException {
        String sql = "SELECT * FROM Computadora WHERE id = ?";
        try (Connection connect = ConectDB.getConnection(); PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ComputadoraModelo(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precio")
                    );
                }
            } catch (SQLException e) {
                throw new SQLException("Error al obtener la computadora", e);
            }
        }
        return null;
    }

    // Obtener las compras de un cliente por su id y por una fecha de inicio y fin
    public List<VentaModelo> findByNitAndDate(String idCliente, String fechaInicio, String fechaFin) throws SQLException {
        List<VentaModelo> ventas = new ArrayList<>();
        String sql = "SELECT * FROM Venta WHERE id_cliente = ? AND fecha BETWEEN ? AND ?";
        try (Connection connect = ConectDB.getConnection(); PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, idCliente);
            ps.setString(2, fechaInicio);
            ps.setString(3, fechaFin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VentaModelo venta = new VentaModelo(
                        rs.getInt("id"),
                        rs.getInt("id_usuario"),
                        rs.getString("id_cliente"),
                        rs.getDouble("total"),
                        rs.getDate("fecha")
                );
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener las ventas", e);
        }
        return ventas;
    }
}
