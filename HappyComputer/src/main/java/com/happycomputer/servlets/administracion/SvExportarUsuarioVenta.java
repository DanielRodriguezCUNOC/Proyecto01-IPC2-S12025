package com.happycomputer.servlets.administracion;

import com.happycomputer.dto.ReporteDevolucionDTO;
import com.happycomputer.dto.ReporteUsuarioVentaDTO;
import com.happycomputer.dto.ReporteVentaDTO;
import com.happycomputer.persistenciadatos.DevolucionDAO;
import com.happycomputer.persistenciadatos.ReporteVentaDAO;
import com.happycomputer.persistenciadatos.VentaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/SvExportarUsuarioVenta")
public class SvExportarUsuarioVenta extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros de la petición
        String fechaInicioStr = request.getParameter("fechaInicio");
        String fechaFinStr = request.getParameter("fechaFin");

        try {
            // Convertir las fechas a tipo Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicio = sdf.parse(fechaInicioStr);
            Date fechaFin = sdf.parse(fechaFinStr);

            // Obtener el reporte del usuario con más ventas
            ReporteVentaDAO reporteVentaDAO = new ReporteVentaDAO();
            ReporteUsuarioVentaDTO reporte = reporteVentaDAO.obtenerUsuarioMasVentas(fechaInicio, fechaFin);

            // Configurar la respuesta para descargar un archivo CSV
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"reporte_usuario_mas_ventas.csv\"");

            // Escribir el contenido del CSV
            PrintWriter out = response.getWriter();

            // Escribir la información del usuario
            out.println("Usuario con Más Ventas");
            out.println("Nombre de Usuario,Total de Ventas");
            out.println(reporte.getNombreUsuario() + "," + reporte.getTotalVentas());
            out.println(); // Línea en blanco para separar secciones

            // Escribir los detalles de las ventas
            out.println("Detalles de las Ventas");
            out.println("ID Venta,Cliente,Fecha Venta,Computadora,Precio Unitario,Cantidad");

            // Obtener las ventas del reporte
            List<ReporteVentaDTO> ventas = reporte.getVentas();
            // Escribir cada venta en el archivo CSV
            for (ReporteVentaDTO venta : ventas) {
                out.println(
                        venta.getIdVenta() + "," +
                                venta.getNombreCliente() + "," +
                                sdf.format(venta.getFechaVenta()) + "," +
                                venta.getNombreComputadora() + "," +
                                venta.getPrecioUnitario() + "," +
                                venta.getCantidad()
                );
            }

            // Cerrar el PrintWriter
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar el reporte de usuario con más ventas.");
        }
    }
}
