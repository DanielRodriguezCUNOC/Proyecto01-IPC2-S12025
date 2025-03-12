package com.happycomputer.servlets.administracion;

import com.happycomputer.dto.ReporteDevolucionDTO;
import com.happycomputer.persistenciadatos.DevolucionDAO;

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

@WebServlet("/SvExportarDevolucion")
public class SvExportarDevolucion extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Se obtienen los parámetros de la petición
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");

        try {
            // Se convierten las fechas a tipo Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicioDate = sdf.parse(fechaInicio);
            Date fechaFinDate = sdf.parse(fechaFin);

            // Se obtienen las devoluciones por intervalo de fechas
            DevolucionDAO devolucionDAO = new DevolucionDAO();
            List<ReporteDevolucionDTO> devoluciones = devolucionDAO.obtenerDevolucionesPorIntervalo(fechaInicioDate, fechaFinDate);

            // Configuración de la respuesta para descargar un archivo CSV
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"reporte_devoluciones.csv\"");

            // Escribir el contenido del CSV
            PrintWriter out = response.getWriter();
            out.println("idDevolucion,idVenta,nombreCliente,fechaDevolucion,nombreComputadora,perdida");

            for (ReporteDevolucionDTO devolucion : devoluciones) {
                // Formatear la fecha de devolución
                String fechaDevolucionStr = sdf.format(devolucion.getFechaDevolucion());
                // Escribir la línea en el archivo CSV
                out.println(devolucion.getIdDevolucion() + ","
                        + devolucion.getIdVenta() + "," +
                        devolucion.getNombreCliente() + "," +
                        fechaDevolucionStr + "," +
                        devolucion.getNombreComputadora() + "," +
                        devolucion.getPerdida());
            }

            // Cerrar el PrintWriter
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar el reporte de devoluciones.");
        }
    }
}