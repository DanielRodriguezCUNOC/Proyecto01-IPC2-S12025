package com.happycomputer.persistenciadatos;

import com.happycomputer.modelos.ClienteModelo;
import com.happycomputer.util.ConectDB;

import java.sql.*;
import java.util.List;

public class ClienteDAO extends CrudDAO<ClienteModelo> {
    @Override
    public ClienteModelo insert(ClienteModelo entity) throws SQLException {
        String sql = "INSERT INTO Cliente (nombre, nit, direccion) VALUES (?, ?, ?)";
        try (Connection conn = ConectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, entity.getNombre());
            ps.setString(2, entity.getNit());
            ps.setString(3, entity.getDireccion());

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
    public void update(ClienteModelo entity) throws SQLException {

    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public ClienteModelo findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<ClienteModelo> findAll() throws SQLException {
        return null;
    }

    // Hallar al cliente por su numero de NIT
    public ClienteModelo findByNit(String nit) throws SQLException {
        String sql = "SELECT * FROM Cliente WHERE nit = ?";
        try (Connection con = ConectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nit);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new ClienteModelo(
                            rs.getString("nit"),
                            rs.getString("nombre"),
                            rs.getString("direccion")
                    );
                }
            }
        }
        return null;
    }
}
