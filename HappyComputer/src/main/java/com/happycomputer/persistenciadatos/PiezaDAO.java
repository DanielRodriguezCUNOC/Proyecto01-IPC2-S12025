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
        try(Connection connect = ConectDB.getConnection(); PreparedStatement stmt = connect.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getNombre());
            stmt.setDouble(2, entity.getCosto());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows > 0) {
               try(ResultSet generatedKeys = stmt.getGeneratedKeys();){
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
        try(Connection connect = ConectDB.getConnection(); PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setString(1, entity.getNombre());
            stmt.setDouble(2, entity.getCosto());
            stmt.setInt(3, entity.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Pieza WHERE id = ?";
        try(Connection connect = ConectDB.getConnection(); PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    @Override
    public PiezaModelo findById(Integer id) throws SQLException {
      String sql = "SELECT * FROM Pieza WHERE id = ?";
        try(Connection connect = ConectDB.getConnection(); PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
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
        try(Connection connect = ConectDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while(rs.next()) {
                piezas.add(new PiezaModelo(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("costo")
                ));
            }
        }
    return piezas;
    }

}
