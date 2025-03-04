package com.happycomputer.persistenciadatos;

import com.happycomputer.modelos.UsuarioModelo;
import com.happycomputer.util.ConectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends CrudDAO<UsuarioModelo>{
    @Override
    public UsuarioModelo insert(UsuarioModelo entity) throws SQLException {
        String sql = "INSERT INTO usuario (usuario, password, idRol) VALUES (?, ?, ?)";
        try(Connection con = ConectDB.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entity.getUsuario());
            stmt.setString(2, entity.getPassword());
            stmt.setInt(3, entity.getIdRol());

            int affectedRows = stmt.executeUpdate();
            if(affectedRows > 0 ){
                try(ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getInt(1));
                    }
                }
            }

        }
        return entity;
    }

    @Override
    public void update(UsuarioModelo entity) throws SQLException {
        String sql = "UPDATE usuario SET usuario = ?, password = ?, estado = ?, id_rol = ? WHERE id = ?";
        try(Connection connect = ConectDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setString(1, entity.getUsuario());
            stmt.setString(2, entity.getPassword());
            stmt.setInt(3, entity.getId());
            stmt.setBoolean(4, entity.getEstado());
            stmt.setInt(5, entity.getIdRol());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try(Connection connect = ConectDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public UsuarioModelo findById(Integer id) throws SQLException{
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try(Connection connect = ConectDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    return new UsuarioModelo(
                        rs.getInt("id"),
                        rs.getString("usuario"),
                        rs.getString("password"),
                        rs.getInt("idRol"),
                        rs.getBoolean("estado")
                    );
                }
            }
        }
        return null;
    }

    public UsuarioModelo findByUsuario (String username) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE username = ?";
        try(Connection connect = ConectDB.getConnection();
            PreparedStatement stmt = connect.prepareStatement(sql)) {
            stmt.setString(1, username);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    return new UsuarioModelo(
                        rs.getInt("id"),
                        rs.getString("usuario"),
                        rs.getString("password"),
                        rs.getInt("idRol"),
                        rs.getBoolean("estado")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<UsuarioModelo> findAll() throws SQLException {
        List<UsuarioModelo> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try(Connection connect = ConectDB.getConnection();
        PreparedStatement stmt = connect.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
              usuarios.add(new UsuarioModelo(
                rs.getInt("id"),
                rs.getString("usuario"),
                rs.getString("password"),
                rs.getInt("idRol"),
                rs.getBoolean("estado")
              ));
            }
        }
        return usuarios;
    }

}
