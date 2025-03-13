package com.happycomputer.servlets.administracion;

import com.happycomputer.dto.ReporteComputadoraMasVendidaDTO;
import com.happycomputer.dto.ReporteComputadoraMenosVendidaDTO;
import com.happycomputer.persistenciadatos.ReporteVentaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/ReporteComputadoraMasVendida")
public class SvReporteComputadora extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("computadoraMasVendida".equals(action)) {
            String fechaInicioStr = request.getParameter("fechaInicio");
            String fechaFinStr = request.getParameter("fechaFin");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date fechaInicio = sdf.parse(fechaInicioStr);
                Date fechaFin = sdf.parse(fechaFinStr);

                ReporteVentaDAO reporteVentaDAO = new ReporteVentaDAO();
                ReporteComputadoraMasVendidaDTO reporte = reporteVentaDAO.obtenerComputadoraMasVendida(fechaInicio, fechaFin);

                request.setAttribute("reporte", reporte);
                request.getRequestDispatcher("/AREA_ADMIN/reporteComputadoraMasVendida.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String fechaInicioStr = request.getParameter("fechaInicio");
            String fechaFinStr = request.getParameter("fechaFin");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date fechaInicio = sdf.parse(fechaInicioStr);
                Date fechaFin = sdf.parse(fechaFinStr);

                ReporteVentaDAO reporteVentaDAO = new ReporteVentaDAO();
                ReporteComputadoraMenosVendidaDTO reporte = reporteVentaDAO.obtenerComputadoraMenosVendida(fechaInicio, fechaFin);

                request.setAttribute("reporte", reporte);
                request.getRequestDispatcher("reporteComputadoraMenosVendida.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
