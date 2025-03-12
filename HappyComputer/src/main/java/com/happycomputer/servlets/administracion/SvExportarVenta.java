package com.happycomputer.servlets.administracion;

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

@WebServlet("/SvExportarVenta")
public class SvExportarVenta extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Se obtienen los parametros de la peticion
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");

        try {
            // Se convierten las fechas a tipo Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaInicioDate = sdf.parse(fechaInicio);
            Date fechaFinDate = sdf.parse(fechaFin);

            // Se obtienen las ventas por intervalo de fechas
            ReporteVentaDAO reporteVentaDAO = new ReporteVentaDAO();
            List<ReporteVentaDTO> ventas = reporteVentaDAO.obtenerVentasPorIntervalo(fechaInicioDate, fechaFinDate);

            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=\"reporte_ventas.csv\"");

            PrintWriter out = response.getWriter();
            out.println("idVenta,nombreCliente,fechaVenta,nombreComputadora,precioUnitario,cantidad,totalVenta");

            for (ReporteVentaDTO venta : ventas) {
                out.println(venta.getIdVenta() + "," +
                        venta.getNombreCliente() + "," +
                        venta.getFechaVenta() + "," +
                        venta.getNombreComputadora() + "," +
                        venta.getPrecioUnitario() + "," +
                        venta.getCantidad() + "," +
                        venta.getTotalVenta());

            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }
}
