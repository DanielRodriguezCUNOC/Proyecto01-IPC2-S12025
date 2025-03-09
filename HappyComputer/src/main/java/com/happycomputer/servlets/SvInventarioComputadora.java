package com.happycomputer.servlets;

import com.happycomputer.modelos.EnsamblarComputadoraModelo;
import com.happycomputer.persistenciadatos.ComputadoraDAO;
import com.happycomputer.persistenciadatos.EnsamblarComputadoraDAO;
import com.happycomputer.persistenciadatos.InventarioComputadoraDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/SvInventarioComputadora")
public class SvInventarioComputadora extends HttpServlet {
    private EnsamblarComputadoraDAO ensamblarComputadoraDAO;
    @Override
    public void init() {
        ensamblarComputadoraDAO = new EnsamblarComputadoraDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if(action == null || action.isEmpty()) {
            try {
                List<EnsamblarComputadoraModelo> computadoras = ensamblarComputadoraDAO.findAll();
                request.setAttribute("computadoras", computadoras);
                request.getRequestDispatcher("/AREA_FABRICA/listarComputadoras.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Error al obtener las computadoras ensambladas", e);
            }
        }else if ("ordenar".equals(action)) {
            String orden = request.getParameter("orden");
            if (orden != null) {
                orden = "ASC";
            }
            try {
                List<EnsamblarComputadoraModelo> computadoras = ensamblarComputadoraDAO.orderByDate(orden);
                request.setAttribute("computadoras", computadoras);
                request.getRequestDispatcher("/AREA_FABRICA/listarComputadoras.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Error al obtener las computadoras agotadas", e);
            }
        }else{
            response.sendRedirect(request.getContextPath() + "/SvInventarioComputadora");
        }
    }
}
