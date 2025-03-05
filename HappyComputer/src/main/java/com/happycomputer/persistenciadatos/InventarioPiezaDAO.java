package com.happycomputer.persistenciadatos;

import com.happycomputer.modelos.InventarioPiezaModelo;
import com.happycomputer.util.ConectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventarioPiezaDAO extends CrudDAO<InventarioPiezaModelo>{

    @Override
    public InventarioPiezaModelo insert(InventarioPiezaModelo entity) throws SQLException {
        String sql = "INSERT INTO inventario_pieza (id_pieza, cantidad) VALUES (?, ?)";
        try(Connection con = ConectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, entity.getIdPieza());
            ps.setInt(2, entity.getCantidad());
            ps.executeUpdate();
            return entity;
        }
    }

    @Override
    public void update(InventarioPiezaModelo entity) throws SQLException {
        String sql = "UPDATE inventario_pieza SET id_pieza = ?, cantidad = ? WHERE id = ?";
        try(Connection con = ConectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, entity.getIdPieza());
            ps.setInt(2, entity.getCantidad());
            ps.setInt(3, entity.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM inventario_pieza WHERE id = ?";
        try(Connection con = ConectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public InventarioPiezaModelo findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM inventario_pieza WHERE id = ?";
        try(Connection con = ConectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    return new InventarioPiezaModelo(
                            rs.getInt("id"),
                            rs.getInt("id_pieza"),
                            rs.getInt("cantidad")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<InventarioPiezaModelo> findAll() throws SQLException {
        List<InventarioPiezaModelo> inventarioPiezaModelos = new ArrayList<>();
        String sql = "SELECT * FROM inventario_pieza";
        try(Connection con = ConectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                inventarioPiezaModelos.add(new InventarioPiezaModelo(
                        rs.getInt("id"),
                        rs.getInt("id_pieza"),
                        rs.getInt("cantidad")
                ));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return inventarioPiezaModelos;
    }

    //Ordenar por cantidad
    public List<InventarioPiezaModelo> orderBy(String order) throws SQLException {
        List<InventarioPiezaModelo> iPMs = new ArrayList<>();
        String sql = "SELECT * FROM inventario_pieza ORDER BY cantidad " + ("desc".equalsIgnoreCase(order) ? "DESC" : "ASC");
        try(Connection con = ConectDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                iPMs.add(new InventarioPiezaModelo(
                        rs.getInt("id"),
                        rs.getInt("id_pieza"),
                        rs.getInt("cantidad")
                ));
            }
            return iPMs;

        }
    }
}
