package com.happycomputer.persistenciadatos;

import com.happycomputer.dto.DetalleVentaDTO;
import com.happycomputer.modelos.DetalleVentaModelo;
import com.happycomputer.util.ConectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    //Funcion para devolver los detalles de una venta
    public List<DetalleVentaDTO> findDetailsBySaleId(Integer idVenta) throws SQLException {
        List<DetalleVentaDTO> detalles = new ArrayList<>();
        String sql = "SELECT dv.id, dv.id_venta, dv.id_computadora, dv.precio, c.nombre " +
                "FROM Detalle_Venta dv " +
                "JOIN Computadora c ON dv.id_computadora = c.id " +
                "WHERE dv.id_venta = ?";
        try (Connection con = ConectDB.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetalleVentaDTO detalle = new DetalleVentaDTO(
                            rs.getInt("id"),
                            rs.getInt("id_venta"),
                            rs.getInt("id_computadora"),
                            rs.getDouble("precio"),
                            rs.getString("nombre")
                    );
                    detalles.add(detalle);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los detalles de la venta", e);
        }
        return detalles;
    }

}
