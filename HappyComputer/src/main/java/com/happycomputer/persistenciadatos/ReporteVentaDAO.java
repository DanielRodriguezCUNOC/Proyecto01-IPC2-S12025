package com.happycomputer.persistenciadatos;

import com.happycomputer.dto.ReporteUsuarioVentaDTO;
import com.happycomputer.dto.ReporteVentaDTO;
import com.happycomputer.util.ConectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReporteVentaDAO {
    // Funcion para obtener ventas por un intervalo de fechas
    public List<ReporteVentaDTO> obtenerVentasPorIntervalo(Date fechaInicio, Date fechaFin) {
        List<ReporteVentaDTO> ventas = new ArrayList<>();
        String sql = "SELECT v.id, c.nombre AS nombreCliente, v.fecha, co.nombre AS nombreComputadora, dv.precio_unidad, dv.cantidad, v.total " +
                "FROM Venta v " +
                "JOIN Cliente c ON v.id_cliente = c.nit " +
                "JOIN Detalle_Venta dv ON v.id = dv.id_venta " +
                "JOIN Computadora co ON dv.id_computadora = co.id " +
                "WHERE v.fecha BETWEEN ? AND ?";

        try (Connection conn = ConectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            ps.setDate(2, new java.sql.Date(fechaFin.getTime()));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ReporteVentaDTO venta = new ReporteVentaDTO();
                venta.setIdVenta(rs.getInt("id"));
                venta.setNombreCliente(rs.getString("nombreCliente"));
                venta.setFechaVenta(rs.getDate("fecha"));
                venta.setNombreComputadora(rs.getString("nombreComputadora"));
                venta.setPrecioUnitario(rs.getDouble("precio_unidad"));
                venta.setCantidad(rs.getInt("cantidad"));
                venta.setTotalVenta(rs.getDouble("total"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }

    // Funcion para obtener el usuario con mas ventas
    public ReporteUsuarioVentaDTO obtenerUsuarioMasVentas(Date fechaInicio, Date fechaFin) {
        ReporteUsuarioVentaDTO reporte = new ReporteUsuarioVentaDTO();
        String sqlUsuarioMasVentas = "SELECT u.id AS idUsuario, u.usuario AS nombreUsuario, COUNT(v.id) AS totalVentas " +
                "FROM Venta v " +
                "JOIN Usuario u ON v.id_usuario = u.id " +
                "WHERE v.fecha BETWEEN ? AND ? " +
                "GROUP BY u.id, u.usuario " +
                "ORDER BY totalVentas DESC " +
                "LIMIT 1";

        String sqlDetalleVentas = "SELECT v.id AS idVenta, c.nombre AS nombreCliente, v.fecha AS fechaVenta, " +
                "co.nombre AS nombreComputadora, dv.precio_unidad AS precioUnitario, dv.cantidad " +
                "FROM Venta v " +
                "JOIN Cliente c ON v.id_cliente = c.nit " +
                "JOIN Detalle_Venta dv ON v.id = dv.id_venta " +
                "JOIN Computadora co ON dv.id_computadora = co.id " +
                "WHERE v.id_usuario = ? AND v.fecha BETWEEN ? AND ?";

        try (Connection con = ConectDB.getConnection();
             PreparedStatement psUsuario = con.prepareStatement(sqlUsuarioMasVentas);
             PreparedStatement psDetalle = con.prepareStatement(sqlDetalleVentas)) {

            // Obtener el usuario con más ventas
            psUsuario.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            psUsuario.setDate(2, new java.sql.Date(fechaFin.getTime()));
            ResultSet rsUsuario = psUsuario.executeQuery();
            // Si se encontró un usuario con ventas
            if (rsUsuario.next()) {
                // Llenar los datos del usuario
                reporte.setIdUsuario(rsUsuario.getInt("idUsuario"));
                reporte.setNombreUsuario(rsUsuario.getString("nombreUsuario"));
                reporte.setTotalVentas(rsUsuario.getInt("totalVentas"));

                // Obtener los detalles de las ventas del usuario
                psDetalle.setInt(1, reporte.getIdUsuario());
                psDetalle.setDate(2, new java.sql.Date(fechaInicio.getTime()));
                psDetalle.setDate(3, new java.sql.Date(fechaFin.getTime()));
                ResultSet rsDetalle = psDetalle.executeQuery();

                List<ReporteVentaDTO> ventas = new ArrayList<>();
                while (rsDetalle.next()) {
                    ReporteVentaDTO venta = new ReporteVentaDTO();
                    venta.setIdVenta(rsDetalle.getInt("idVenta"));
                    venta.setNombreCliente(rsDetalle.getString("nombreCliente"));
                    venta.setFechaVenta(rsDetalle.getDate("fechaVenta"));
                    venta.setNombreComputadora(rsDetalle.getString("nombreComputadora"));
                    venta.setPrecioUnitario(rsDetalle.getDouble("precioUnitario"));
                    venta.setCantidad(rsDetalle.getInt("cantidad"));
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
