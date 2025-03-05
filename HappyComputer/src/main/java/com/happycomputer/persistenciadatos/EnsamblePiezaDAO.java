package com.happycomputer.persistenciadatos;

import com.happycomputer.modelos.EnsamblePiezaModelo;
import com.happycomputer.util.ConectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnsamblePiezaDAO extends CrudDAO<EnsamblePiezaModelo>{
    @Override
    public EnsamblePiezaModelo insert(EnsamblePiezaModelo entity) throws SQLException {
        String sql = "INSERT INTO Ensamble_Pieza (id_computadora, id_pieza, cantidad) VALUES (?, ?, ?)";
        try (Connection connect = ConectDB.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, entity.getIdComputadora());
            ps.setInt(2, entity.getIdPieza());
            ps.setInt(3, entity.getCantidad());
            ps.executeUpdate();
        }
        return entity;
    }

    @Override
    public void update(EnsamblePiezaModelo entity) throws SQLException {
        String sql = "UPDATE Ensamble_Pieza SET cantidad = ? WHERE id_computadora = ? AND id_pieza = ?";
        try (Connection connect = ConectDB.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, entity.getCantidad());
            ps.setInt(2, entity.getIdComputadora());
            ps.setInt(3, entity.getIdPieza());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        // No se usa un ID único, eliminamos por combinación de claves
        throw new UnsupportedOperationException("Use deleteByComputadoraYPieza en su lugar.");
    }

    public void deleteByComputadoraYPieza(int idComputadora, int idPieza) throws SQLException {
        String sql = "DELETE FROM Ensamble_Pieza WHERE id_computadora = ? AND id_pieza = ?";
        try (Connection connect = ConectDB.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, idComputadora);
            ps.setInt(2, idPieza);
            ps.executeUpdate();
        }
    }

    @Override
    public EnsamblePiezaModelo findById(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Use findByComputadoraYPieza en su lugar.");
    }

    public EnsamblePiezaModelo findByComputadoraYPieza(int idComputadora, int idPieza) throws SQLException {
        String sql = "SELECT * FROM Ensamble_Pieza WHERE id_computadora = ? AND id_pieza = ?";
        try (Connection connect = ConectDB.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, idComputadora);
            ps.setInt(2, idPieza);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    EnsamblePiezaModelo ePM =  new EnsamblePiezaModelo();
                    ePM.setIdComputadora(rs.getInt("id_computadora"));
                    ePM.setIdPieza(rs.getInt("id_pieza"));
                    ePM.setCantidad(rs.getInt("cantidad"));
                    return ePM;
                }
            }
        }
        return null;
    }

    @Override
    public List<EnsamblePiezaModelo> findAll() throws SQLException {
        List<EnsamblePiezaModelo> ensamblajes = new ArrayList<>();
        String sql = "SELECT * FROM Ensamble_Pieza";
        try (Connection connect = ConectDB.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                EnsamblePiezaModelo ePM = new EnsamblePiezaModelo();
                ePM.setIdComputadora(rs.getInt("id_computadora"));
                ePM.setIdPieza(rs.getInt("id_pieza"));
                ePM.setCantidad(rs.getInt("cantidad"));
                ensamblajes.add(ePM);
            }
        }
        return ensamblajes;
    }
}
