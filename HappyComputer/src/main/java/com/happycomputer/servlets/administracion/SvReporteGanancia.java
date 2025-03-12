package com.happycomputer.servlets.administracion;

import com.happycomputer.dto.ReporteGananciaDTO;
import com.happycomputer.persistenciadatos.ReporteGananciaDAO;
import com.happycomputer.persistenciadatos.VentaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/SvReporteGanancia")
public class SvReporteGanancia extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fechaInicioStr = request.getParameter("fechaInicio");
        String fechaFinStr = request.getParameter("fechaFin");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaInicio = sdf.parse(fechaInicioStr);
            Date fechaFin = sdf.parse(fechaFinStr);

            ReporteGananciaDAO reporteGananciaDAO = new ReporteGananciaDAO();
            List<ReporteGananciaDTO> ganancias = reporteGananciaDAO.obtenerGananciasPorIntervalo(fechaInicio, fechaFin);

            request.setAttribute("ganancias", ganancias);
            request.getRequestDispatcher("reporteGanancias.jsp").forward(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
