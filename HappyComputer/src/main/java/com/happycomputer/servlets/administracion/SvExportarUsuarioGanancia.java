package com.happycomputer.servlets.administracion;

import com.happycomputer.dto.ReporteGananciaDTO;
import com.happycomputer.dto.ReporteGananciaUsuarioDTO;
import com.happycomputer.persistenciadatos.ReporteGananciaDAO;

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

@WebServlet("/SvExportarUsuarioGanancia")
public class SvExportarUsuarioGanancia extends HttpServlet {

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

            // Obtener el reporte del usuario con más ganancias
            ReporteGananciaDAO reporteGananciaDAO = new ReporteGananciaDAO();
            ReporteGananciaUsuarioDTO reporte = reporteGananciaDAO.obtenerUsuarioMasGanancias(fechaInicio, fechaFin);

            // Configurar la respuesta para descargar un archivo CSV
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"reporte_ganancias.csv\"");

            // Escribir el contenido del CSV
            PrintWriter out = response.getWriter();

            // Escribir la información del usuario
            out.println("Usuario con Más Ganancias");
            out.println("Nombre de Usuario,Total de Ganancias");
            out.println(reporte.getNombreUsuario() + "," + reporte.getTotalGanancias());
            out.println(); // Línea en blanco para separar secciones

            // Escribir los detalles de las ventas
            out.println("Ganancias por Intervalo de Fechas");
            out.println("ID Venta,Cliente,Fecha Venta,Computadora,Precio Venta,Costo Producción,Ganancia");
            List<ReporteGananciaDTO> ventas = reporte.getVentas();
            for (ReporteGananciaDTO venta : ventas) {
                out.println(venta.getIdVenta() + "," +
                        venta.getNombreCliente() + "," +
                        venta.getFechaVenta() + "," +
                        venta.getNombreComputadora() + "," +
                        venta.getPrecioVenta() + "," +
                        venta.getCostoProduccion() + "," +
                        venta.getGanancia());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
