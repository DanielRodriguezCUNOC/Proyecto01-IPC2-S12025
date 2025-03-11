package com.happycomputer.persistenciadatos;

import com.happycomputer.modelos.ComputadoraModelo;
import com.happycomputer.util.ConectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComputadoraDAO extends CrudDAO<ComputadoraModelo> {

    @Override
    public ComputadoraModelo insert(ComputadoraModelo entity) throws SQLException {
        String sql = "INSERT INTO Computadora (nombre, precio_venta) VALUES (?, ?)";
        try (Connection connect = ConectDB.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getNombre());
            ps.setDouble(2, entity.getPrecioVenta());
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
    public void update(ComputadoraModelo entity) throws SQLException {
        String sql = "UPDATE Computadora SET nombre = ?, precio_venta = ? WHERE id = ?";
        try (Connection connect = ConectDB.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, entity.getNombre());
            ps.setDouble(2, entity.getPrecioVenta());
            ps.setInt(3, entity.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Computadora WHERE id = ?";
        try (Connection connect = ConectDB.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public ComputadoraModelo findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM Computadora WHERE id = ?";
        try (Connection connect = ConectDB.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ComputadoraModelo(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precio_venta")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<ComputadoraModelo> findAll() throws SQLException {
        List<ComputadoraModelo> computadoras = new ArrayList<>();
        String sql = "SELECT * FROM Computadora";
        try (Connection connect = ConectDB.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                computadoras.add(new ComputadoraModelo(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio_venta")
                ));
            }
        }
        return computadoras;
    }

    // Verificar si la computadora ya existe
    public boolean findByNombre(String nombre) throws SQLException {
        String sql = "SELECT * FROM Computadora WHERE nombre = ?";
        try (Connection connect = ConectDB.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    // Funcion para obtener todas las computadoras disponibles
    public List<ComputadoraModelo> findByEstado(boolean estado) throws SQLException {
        List<ComputadoraModelo> computadoras = new ArrayList<>();
        String sql = "SELECT * FROM Computadora WHERE estado = ?";
        try (Connection connect = ConectDB.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setBoolean(1, estado);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    computadoras.add(new ComputadoraModelo(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precio_venta")
                    ));
                }
            }
        }
        return computadoras;
    }

}
