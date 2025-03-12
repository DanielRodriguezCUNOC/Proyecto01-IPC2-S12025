package com.happycomputer.servlets.administracion;

import com.happycomputer.dto.ReporteGananciaDTO;
import com.happycomputer.dto.ReporteUsuarioVentaDTO;
import com.happycomputer.dto.ReporteVentaDTO;
import com.happycomputer.persistenciadatos.ReporteGananciaDAO;
import com.happycomputer.persistenciadatos.ReporteVentaDAO;

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

@WebServlet("/SvExportarGanancia")
public class SvExportarGanancia extends HttpServlet {
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
            ReporteGananciaDAO reporteGananciaDAO = new ReporteGananciaDAO();
            List<ReporteGananciaDTO> ganancias = reporteGananciaDAO.obtenerGananciasPorIntervalo(fechaInicio, fechaFin);

            // Configurar la respuesta para descargar un archivo CSV
            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"reporte_ganancias.csv\"");

            // Escribir el contenido del CSV
            PrintWriter out = response.getWriter();

            // Escribir la información del usuario
            out.println("Ganancias por Intervalo de Fechas");
            out.println("ID Venta,Cliente,Fecha Venta,Computadora,Precio Venta,Costo Producción,Ganancia");
            // Escribir los detalles de las ventas
            for (ReporteGananciaDTO ganancia : ganancias) {
                // Formatear la fecha de venta
                String fechaVentaStr = sdf.format(ganancia.getFechaVenta());
                // Escribir la línea en el archivo CSV
                out.println(ganancia.getIdVenta() + ","
                        + ganancia.getNombreCliente() + "," +
                        fechaVentaStr + "," +
                        ganancia.getNombreComputadora() + "," +
                        ganancia.getPrecioVenta() + "," +
                        ganancia.getCostoProduccion() + "," +
                        ganancia.getGanancia());
            }


            // Obtener las ventas del reporte

            // Escribir cada venta en el archivo CSV

            // Cerrar el PrintWriter
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al generar el reporte de usuario con más ventas.");
        }
    }
}
