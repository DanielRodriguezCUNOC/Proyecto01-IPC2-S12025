package com.happycomputer.servlets.administracion;

import com.happycomputer.dto.ReporteUsuarioVentaDTO;
import com.happycomputer.persistenciadatos.ReporteVentaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/SvReporteUsuario")
public class SvReporteUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        if ("masVentas".equals(action)) {
            String fechaInicioStr = request.getParameter("fechaInicio");
            String fechaFinStr = request.getParameter("fechaFin");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date fechaInicio = sdf.parse(fechaInicioStr);
                Date fechaFin = sdf.parse(fechaFinStr);

                ReporteVentaDAO reporteVentaDAO = new ReporteVentaDAO();
                ReporteUsuarioVentaDTO reporte = reporteVentaDAO.obtenerUsuarioMasVentas(fechaInicio, fechaFin);

                request.setAttribute("reporte", reporte);
                request.getRequestDispatcher("reporteUsuarioMasVentas.jsp").forward(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {

        }
    }
}
