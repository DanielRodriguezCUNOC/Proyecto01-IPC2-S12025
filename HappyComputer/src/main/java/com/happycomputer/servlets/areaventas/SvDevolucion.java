package com.happycomputer.servlets.areaventas;

import com.happycomputer.modelos.DevolucionModelo;
import com.happycomputer.persistenciadatos.DevolucionDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/SvDevolucion")
public class SvDevolucion extends HttpServlet {
    private DevolucionDAO devolucionDAO;

    @Override
    public void init() {
        devolucionDAO = new DevolucionDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nit = request.getParameter("nit");
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");
        try {
            List<DevolucionModelo> devoluciones = devolucionDAO.findByCostumer(nit, fechaInicio, fechaFin);
            request.setAttribute("devoluciones", devoluciones);
            request.getRequestDispatcher("/AREA_VENTAS/listarDevoluciones.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idVenta = Integer.parseInt(request.getParameter("idVenta"));
        String fechaDevolucion = request.getParameter("fechaDevolucion");
        double perdida = Double.parseDouble(request.getParameter("perdida"));

        DevolucionModelo devolucionModelo = new DevolucionModelo(
                null,
                idVenta,
                fechaDevolucion,
                perdida
        );

    }
}
