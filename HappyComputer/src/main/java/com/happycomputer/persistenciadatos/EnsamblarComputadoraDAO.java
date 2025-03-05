package com.happycomputer.persistenciadatos;

import com.happycomputer.modelos.EnsamblarComputadoraModelo;
import com.happycomputer.util.ConectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnsamblarComputadoraDAO extends CrudDAO<EnsamblarComputadoraModelo> {
    @Override
    public EnsamblarComputadoraModelo insert(EnsamblarComputadoraModelo entity) throws SQLException {
        String sql = "INSERT INTO Ensamblar_Computadora (id_computadora, id_usuario, fecha_ensamble) VALUES (?, ?, ?)";
        try(Connection con = ConectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, entity.getIdComputadora());
            ps.setInt(2, entity.getIdUsuario());
            ps.setDate(3, new java.sql.Date(entity.getFechaEnsamble().getTime()));
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()) {
                if(rs.next()) {
                    entity.setId(rs.getInt(1));
                }
            }
            return entity;

        }
    }

    @Override
    public void update(EnsamblarComputadoraModelo entity) throws SQLException {
        String sql = "UPDATE Ensamblar_Computadora SET id_computadora = ?, id_usuario = ?, fecha_ensamble = ? WHERE id = ?";
        try(Connection con = ConectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, entity.getIdComputadora());
            ps.setInt(2, entity.getIdUsuario());
            ps.setDate(3, new java.sql.Date(entity.getFechaEnsamble().getTime()));
            ps.setInt(4, entity.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Ensamblar_Computadora WHERE id_computadora = ?";
        try(Connection con = ConectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public EnsamblarComputadoraModelo findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM Ensamblar_Computadora WHERE id_computadora = ?";
        try(Connection con = ConectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return new EnsamblarComputadoraModelo(
                            rs.getInt("id"),
                            rs.getInt("id_computadora"),
                            rs.getInt("id_usuario"),
                            rs.getInt("costo_ensamble"),
                            rs.getDate("fecha_ensamble")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<EnsamblarComputadoraModelo> findAll() throws SQLException {
        List<EnsamblarComputadoraModelo> listaEnsambles = new ArrayList<>();
        String sql = "SELECT * FROM Ensamblar_Computadora";
        try(Connection con = ConectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                listaEnsambles.add(new EnsamblarComputadoraModelo(
                        rs.getInt("id"),
                        rs.getInt("id_computadora"),
                        rs.getInt("id_usuario"),
                        rs.getInt("costo_ensamble"),
                        rs.getDate("fecha_ensamble")
                ));
            }
        }
        return listaEnsambles;
    }
}
