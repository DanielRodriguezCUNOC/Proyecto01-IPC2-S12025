package com.happycomputer.persistenciadatos;

import com.happycomputer.dto.ReporteGananciaDTO;
import com.happycomputer.util.ConectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReporteGananciaDAO {

    // Obtenemos las ganaancias por intervalo de fechas
    public List<ReporteGananciaDTO> obtenerGananciasPorIntervalo(Date fechaInicio, Date fechaFin) {
        List<ReporteGananciaDTO> ganancias = new ArrayList<>();
        // Generamos la consulta SQL
        String sql = "SELECT v.id AS idVenta, c.nombre AS nombreCliente, v.fecha AS fechaVenta, " +
                "co.nombre AS nombreComputadora, dv.precio_unidad AS precioVenta, " +
                "ec.costo AS costoProduccion, " +
                "(dv.precio_unidad - ec.costo) AS ganancia " +
                "FROM Venta v " +
                "JOIN Cliente c ON v.id_cliente = c.nit " +
                "JOIN Detalle_Venta dv ON v.id = dv.id_venta " +
                "JOIN Computadora co ON dv.id_computadora = co.id " +
                "JOIN Ensamblar_Computadora ec ON co.id = ec.id_computadora " +
                "WHERE v.fecha BETWEEN ? AND ?";

        try (Connection con = ConectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            ps.setDate(2, new java.sql.Date(fechaFin.getTime()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ReporteGananciaDTO ganancia = new ReporteGananciaDTO();
                ganancia.setIdVenta(rs.getInt("idVenta"));
                ganancia.setNombreCliente(rs.getString("nombreCliente"));
                ganancia.setFechaVenta(rs.getDate("fechaVenta"));
                ganancia.setNombreComputadora(rs.getString("nombreComputadora"));
                ganancia.setPrecioVenta(rs.getDouble("precioVenta"));
                ganancia.setCostoProduccion(rs.getDouble("costoProduccion"));
                ganancia.setGanancia(rs.getDouble("ganancia"));
                ganancias.add(ganancia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ganancias;
    }
}
