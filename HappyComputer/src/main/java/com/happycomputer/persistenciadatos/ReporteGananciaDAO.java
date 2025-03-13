package com.happycomputer.persistenciadatos;

import com.happycomputer.dto.ReporteGananciaDTO;
import com.happycomputer.dto.ReporteGananciaUsuarioDTO;
import com.happycomputer.util.ConectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReporteGananciaDAO {

    // Obtenemos las ganancias por intervalo de fechas
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

    //Obtenemos las ganancias generadas por un usuario
    public ReporteGananciaUsuarioDTO obtenerUsuarioMasGanancias(Date fechaInicio, Date fechaFin) {
        ReporteGananciaUsuarioDTO reporte = new ReporteGananciaUsuarioDTO();
        String sqlUsuarioMasGanancias = "SELECT u.id AS idUsuario, u.usuario AS nombreUsuario, SUM(dv.precio_unidad - ec.costo) AS totalGanancias " +
                "FROM Venta v " +
                "JOIN Usuario u ON v.id_usuario = u.id " +
                "JOIN Detalle_Venta dv ON v.id = dv.id_venta " +
                "JOIN Computadora co ON dv.id_computadora = co.id " +
                "JOIN Ensamblar_Computadora ec ON co.id = ec.id_computadora " +
                "WHERE v.fecha BETWEEN ? AND ? " +
                "GROUP BY u.id, u.usuario " +
                "ORDER BY totalGanancias DESC " +
                "LIMIT 1";

        String sqlDetalleVentas = "SELECT v.id AS idVenta, c.nombre AS nombreCliente, v.fecha AS fechaVenta, " +
                "co.nombre AS nombreComputadora, dv.precio_unidad AS precioVenta, ec.costo AS costoProduccion, " +
                "(dv.precio_unidad - ec.costo) AS ganancia " +
                "FROM Venta v " +
                "JOIN Cliente c ON v.id_cliente = c.nit " +
                "JOIN Detalle_Venta dv ON v.id = dv.id_venta " +
                "JOIN Computadora co ON dv.id_computadora = co.id " +
                "JOIN Ensamblar_Computadora ec ON co.id = ec.id_computadora " +
                "WHERE v.id_usuario = ? AND v.fecha BETWEEN ? AND ?";

        try (Connection con = ConectDB.getConnection();
             PreparedStatement psUsuario = con.prepareStatement(sqlUsuarioMasGanancias);
             PreparedStatement psDetalle = con.prepareStatement(sqlDetalleVentas)) {

            // Obtener el usuario con más ganancias
            psUsuario.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            psUsuario.setDate(2, new java.sql.Date(fechaFin.getTime()));
            ResultSet rsUsuario = psUsuario.executeQuery();

            if (rsUsuario.next()) {
                reporte.setIdUsuario(rsUsuario.getInt("idUsuario"));
                reporte.setNombreUsuario(rsUsuario.getString("nombreUsuario"));
                reporte.setTotalGanancias(rsUsuario.getDouble("totalGanancias"));

                // Obtener los detalles de las ventas del usuario
                psDetalle.setInt(1, reporte.getIdUsuario());
                psDetalle.setDate(2, new java.sql.Date(fechaInicio.getTime()));
                psDetalle.setDate(3, new java.sql.Date(fechaFin.getTime()));
                ResultSet rsDetalle = psDetalle.executeQuery();

                List<ReporteGananciaDTO> ventas = new ArrayList<>();
                while (rsDetalle.next()) {
                    ReporteGananciaDTO venta = new ReporteGananciaDTO();
                    venta.setIdVenta(rsDetalle.getInt("idVenta"));
                    venta.setNombreCliente(rsDetalle.getString("nombreCliente"));
                    venta.setFechaVenta(rsDetalle.getDate("fechaVenta"));
                    venta.setNombreComputadora(rsDetalle.getString("nombreComputadora"));
                    venta.setPrecioVenta(rsDetalle.getDouble("precioVenta"));
                    venta.setCostoProduccion(rsDetalle.getDouble("costoProduccion"));
                    venta.setGanancia(rsDetalle.getDouble("ganancia"));
                    ventas.add(venta);
                }
                reporte.setVentas(ventas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reporte;
    }
}
