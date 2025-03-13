package com.happycomputer.servlets.administracion;

import com.happycomputer.dto.ReporteComputadoraMasVendidaDTO;
import com.happycomputer.dto.ReporteComputadoraMenosVendidaDTO;
import com.happycomputer.dto.ReporteVentaDTO;
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

@WebServlet("/ExportarComputadora")
public class SvExportarComputadora extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Generar el archivo csv con la información de la computadora mas vendida
        String action = request.getParameter("action");
        if ("computadoraMasVendida".equals(action)) {

            // Obtener los parámetros de la petición
            String fechaInicioStr = request.getParameter("fechaInicio");
            String fechaFinStr = request.getParameter("fechaFin");

            try {
                // Convertir las fechas a tipo Date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicio = sdf.parse(fechaInicioStr);
                Date fechaFin = sdf.parse(fechaFinStr);

                ReporteVentaDAO reporteVentaDAO = new ReporteVentaDAO();
                ReporteComputadoraMasVendidaDTO reporte = reporteVentaDAO.obtenerComputadoraMasVendida(fechaInicio, fechaFin);

                // Configurar la respuesta para descargar un archivo CSV
                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", "attachment; filename=\"reporte_computadora_mas_vendida.csv\"");

                PrintWriter out = response.getWriter();

                // Escribir la información de la computadora más vendida
                out.println("Computadora más vendida");
                out.println("ID Computadora,Nombre Computadora,Cantidad Vendida");
                out.println(reporte.getIdComputadora() + ","
                        + reporte.getNombreComputadora() + ","
                        + reporte.getTotalVentas());
                // Línea en blanco para separar secciones
                out.println();

                // Escribir los detalles de las ventas
                out.println("Detalles de las Ventas");
                out.println("ID Venta,Cliente,Fecha Venta,Computadora,Precio Unitario,Cantidad");
                List<ReporteVentaDTO> ventas = reporte.getVentas();
                for (ReporteVentaDTO venta : ventas) {
                    out.println(venta.getIdVenta() + ","
                            + venta.getNombreCliente() + ","
                            + venta.getFechaVenta() + ","
                            + venta.getNombreComputadora() + ","
                            + venta.getPrecioUnitario() + ","
                            + venta.getCantidad());
                }

                // Cerrar el archivo
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Obtener los parámetros de la petición
            String fechaInicioStr = request.getParameter("fechaInicio");
            String fechaFinStr = request.getParameter("fechaFin");

            try {
                // Convertir las fechas a tipo Date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicio = sdf.parse(fechaInicioStr);
                Date fechaFin = sdf.parse(fechaFinStr);

                ReporteVentaDAO reporteVentaDAO = new ReporteVentaDAO();
                ReporteComputadoraMenosVendidaDTO reporte = reporteVentaDAO.obtenerComputadoraMenosVendida(fechaInicio, fechaFin);

                // Configurar la respuesta para descargar un archivo CSV
                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", "attachment; filename=\"reporte_computadora_menos_vendida.csv\"");

                PrintWriter out = response.getWriter();

                // Escribir la información de la computadora menos vendida
                out.println("Computadora más vendida");
                out.println("ID Computadora,Nombre Computadora,Cantidad Vendida");
                out.println(reporte.getIdComputadora() + ","
                        + reporte.getNombreComputadora() + ","
                        + reporte.getTotalVentas());
                // Línea en blanco para separar secciones
                out.println();

                // Escribir los detalles de las ventas
                out.println("Detalles de las Ventas");
                out.println("ID Venta,Cliente,Fecha Venta,Computadora,Precio Unitario,Cantidad");
                List<ReporteVentaDTO> ventas = reporte.getVentas();
                for (ReporteVentaDTO venta : ventas) {
                    out.println(venta.getIdVenta() + ","
                            + venta.getNombreCliente() + ","
                            + venta.getFechaVenta() + ","
                            + venta.getNombreComputadora() + ","
                            + venta.getPrecioUnitario() + ","
                            + venta.getCantidad());
                }

                // Cerrar el archivo
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
