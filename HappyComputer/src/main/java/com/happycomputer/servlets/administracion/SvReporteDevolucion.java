package com.happycomputer.servlets.administracion;

import com.happycomputer.dto.ReporteDevolucionDTO;
import com.happycomputer.persistenciadatos.DevolucionDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/SvReporteDevolucion")
public class SvReporteDevolucion extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fechaInicioStr = request.getParameter("fechaInicio");
        String fechaFinStr = request.getParameter("fechaFin");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaInicio = sdf.parse(fechaInicioStr);
            Date fechaFin = sdf.parse(fechaFinStr);

            DevolucionDAO devolucionDAO = new DevolucionDAO();
            List<ReporteDevolucionDTO> devoluciones = devolucionDAO.obtenerDevolucionesPorIntervalo(fechaInicio, fechaFin);

            request.setAttribute("devoluciones", devoluciones);
            request.getRequestDispatcher("reporteDevoluciones.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
