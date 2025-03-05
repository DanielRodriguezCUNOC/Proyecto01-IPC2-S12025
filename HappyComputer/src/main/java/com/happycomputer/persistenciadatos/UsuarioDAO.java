package com.happycomputer.persistenciadatos;

import com.happycomputer.modelos.UsuarioModelo;
import com.happycomputer.util.ConectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends CrudDAO<UsuarioModelo>{
    @Override
    public UsuarioModelo insert(UsuarioModelo entity) throws SQLException {
        String sql = "INSERT INTO Usuario (usuario, password, id_rol) VALUES (?, ?, ?)";
        try(Connection con = ConectDB.getConnection();
        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getUsuario());
            ps.setString(2, entity.getPassword());
            ps.setInt(3, entity.getIdRol());

            int affectedRows = ps.executeUpdate();
            if(affectedRows > 0 ){
                try(ResultSet generatedKeys = ps.getGeneratedKeys()) {
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
            PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, entity.getUsuario());
            ps.setString(2, entity.getPassword());
            ps.setInt(3, entity.getId());
            ps.setBoolean(4, entity.getEstado());
            ps.setInt(5, entity.getIdRol());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try(Connection connect = ConectDB.getConnection();
            PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public UsuarioModelo findById(Integer id) throws SQLException{
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try(Connection connect = ConectDB.getConnection();
            PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
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
            PreparedStatement ps = connect.prepareStatement(sql)) {
            ps.setString(1, username);
            try(ResultSet rs = ps.executeQuery()) {
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
        PreparedStatement ps = connect.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {
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

    // Buscar un usuario por el Rol
    public List<UsuarioModelo> findByRol(Integer idRol) throws SQLException {
        List<UsuarioModelo> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario WHERE id_rol = ?";
        try (Connection connect = ConectDB.getConnection();
             PreparedStatement ps = connect.prepareStatement(sql)) {
            if (connect == null) {
                throw new SQLException("No se pudo establecer la conexión a la base de datos");
            }
            ps.setInt(1, idRol);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String usuario = rs.getString("usuario");
                    String password = rs.getString("password");
                    int idRolDb = rs.getInt("id_rol");  // Asegúrate de que el nombre sea correcto
                    boolean estado = rs.getBoolean("estado");

                    usuarios.add(new UsuarioModelo(id, usuario, password, idRolDb, estado));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime la traza de la excepción
            throw new SQLException("Error al buscar usuarios por rol: " + e.getMessage());
        }
        return usuarios;
    }

}
