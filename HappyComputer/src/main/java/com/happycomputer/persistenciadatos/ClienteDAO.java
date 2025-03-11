package com.happycomputer.persistenciadatos;

import com.happycomputer.modelos.ClienteModelo;
import com.happycomputer.util.ConectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteDAO extends CrudDAO {
    @Override
    public Object insert(Object entity) throws SQLException {
        return null;
    }

    @Override
    public void update(Object entity) throws SQLException {

    }

    @Override
    public void delete(Integer id) throws SQLException {

    }

    @Override
    public Object findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<ClienteModelo> findAll() throws SQLException {
        return null;
    }

    // Hallar al cliente por su numero de NIT
    public ClienteModelo findByNit(String nit) throws SQLException {
        String sql = "select * from ClienteModelo where nit = ?";
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
