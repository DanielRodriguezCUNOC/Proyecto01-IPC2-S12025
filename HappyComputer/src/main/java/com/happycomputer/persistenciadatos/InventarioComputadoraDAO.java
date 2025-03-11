package com.happycomputer.persistenciadatos;

import com.happycomputer.modelos.InventarioComputadoraModelo;
import com.happycomputer.util.ConectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioComputadoraDAO extends CrudDAO<InventarioComputadoraModelo> {
    @Override
    public InventarioComputadoraModelo insert(InventarioComputadoraModelo entity) throws SQLException {
        String sql = "INSERT INTO Inventario_Computadora (id_ensamblar_computadora, cantidad) VALUES (?, ?)";
        try (Connection con = ConectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, entity.getIdEnsamblarComputadora());
            ps.setInt(2, entity.getCantidad());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }
        return entity;
    }

    @Override
    public void update(InventarioComputadoraModelo entity) throws SQLException {
        String sql = "UPDATE Inventario_Computadora SET id_ensamblar_computadora = ?, cantidad = ? WHERE id = ?";
        try (Connection con = ConectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, entity.getIdEnsamblarComputadora());
            ps.setInt(2, entity.getCantidad());
            ps.setInt(3, entity.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Inventario_Computadora WHERE id = ?";
        try (Connection con = ConectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public InventarioComputadoraModelo findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM Inventario_Computadora WHERE id = ?";
        try (Connection con = ConectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new InventarioComputadoraModelo(
                            rs.getInt("id"),
                            rs.getInt("id_ensamblar_computadora"),
                            rs.getInt("cantidad")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<InventarioComputadoraModelo> findAll() throws SQLException {
        List<InventarioComputadoraModelo> inventarioComputadoraList = new ArrayList<>();
        String sql = "SELECT * FROM Inventario_Computadora";
        try (Connection con = ConectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                inventarioComputadoraList.add(new InventarioComputadoraModelo(
                        rs.getInt("id"),
                        rs.getInt("id_ensamblar_computadora"),
                        rs.getInt("cantidad")
                ));
            }
        }
        return inventarioComputadoraList;
    }

    // Metodo para ordenar por cantidad
    public List<InventarioComputadoraModelo> orderBy(String order) throws SQLException {
        List<InventarioComputadoraModelo> iCMs = new ArrayList<>();
        String sql = "SELECT * FROM Inventario_Computadora ORDER BY cantidad " + order;
        try (Connection con = ConectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                iCMs.add(new InventarioComputadoraModelo(
                        rs.getInt("id"),
                        rs.getInt("id_ensamblar_computadora"),
                        rs.getInt("cantidad")
                ));
            }
        }
        return iCMs;
    }

    //Metodo para obtener todas las computadoras con una cantidad >= 1
    public List<InventarioComputadoraModelo> findByCantidadGreaterThanZero() throws SQLException {
        List<InventarioComputadoraModelo> iCMs = new ArrayList<>();
        String sql = "SELECT * FROM Inventario_Computadora WHERE cantidad >= 1";
        try (Connection con = ConectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                iCMs.add(new InventarioComputadoraModelo(
                        rs.getInt("id"),
                        rs.getInt("id_ensamblar_computadora"),
                        rs.getInt("cantidad")
                ));
            }
        }
        return iCMs;
    }

}
