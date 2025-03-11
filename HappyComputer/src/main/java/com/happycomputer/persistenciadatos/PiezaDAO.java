package com.happycomputer.persistenciadatos;

import com.happycomputer.modelos.PiezaModelo;
import com.happycomputer.util.ConectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PiezaDAO extends CrudDAO<PiezaModelo> {

    @Override
    public PiezaModelo insert(PiezaModelo entity) throws SQLException {
        String sql = "INSERT INTO Pieza (nombre, costo) VALUES (?, ?)";
        try (Connection connect = ConectDB.getConnection(); PreparedStatement ps = connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getNombre());
            ps.setDouble(2, entity.getCosto());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys();) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getInt(1));
                    }
                }
            }

        }
        return entity;
    }

    @Override
    public void update(PiezaModelo entity) throws SQLException {
        String sql = "UPDATE Pieza SET nombre = ?, costo = ? WHERE id = ?";
        try (Connection connect = ConectDB.getConnection(); PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, entity.getNombre());
            ps.setDouble(2, entity.getCosto());
            ps.setInt(3, entity.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Pieza WHERE id = ?";
        try (Connection connect = ConectDB.getConnection(); PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public PiezaModelo findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM Pieza WHERE id = ?";
        try (Connection connect = ConectDB.getConnection(); PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PiezaModelo(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("costo")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<PiezaModelo> findAll() throws SQLException {
        List<PiezaModelo> piezas = new ArrayList<>();
        String sql = "SELECT * FROM Pieza";
        try (Connection connect = ConectDB.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                piezas.add(new PiezaModelo(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("costo")
                ));
            }
        }
        return piezas;
    }

    //Funcion para saber si una pieza ya existe en la tabla Pieza
    public boolean existePieza(String nombre) throws SQLException {
        String sql = "SELECT * COUNT(*) FROM Pieza WHERE nombre = ?";
        try (Connection connect = ConectDB.getConnection(); PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    //Verificar que la tabla esta vacia
    public boolean isEmpty() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Pieza";
        try (Connection connect = ConectDB.getConnection(); PreparedStatement ps = connect.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        }
        return false;
    }

    // Metodo para resetear el valor autoincremental
    public void resetAutoIncrement() throws SQLException {
        String sql = "ALTER TABLE Pieza AUTO_INCREMENT = 1"; // Para MySQL
        try (Connection con = ConectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.executeUpdate(sql);
        }
    }

    // Metodo para cambiar el estado de una pieza
    public void cambiarEstado(Integer id, boolean estado) throws SQLException {
        String sql = "UPDATE Pieza SET estado = ? WHERE id = ?";
        try (Connection con = ConectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, estado);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

}
