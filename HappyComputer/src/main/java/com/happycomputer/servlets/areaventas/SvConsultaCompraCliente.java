package com.happycomputer.servlets.areaventas;

import com.happycomputer.modelos.VentaModelo;
import com.happycomputer.persistenciadatos.VentaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/SvConsultaCompraCliente")
public class SvConsultaCompraCliente extends HttpServlet {
    private VentaDAO ventaDAO;

    @Override
    public void init() {
        ventaDAO = new VentaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nit = request.getParameter("nit");
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");

        try {
            List<VentaModelo> ventas = ventaDAO.findByNitAndDate(nit, fechaInicio, fechaFin);
            request.setAttribute("ventas", ventas);
            request.getRequestDispatcher("/AREA_VENTAS/consultaCompraCliente.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al obtener las computadoras", e);
        }
    }


}
