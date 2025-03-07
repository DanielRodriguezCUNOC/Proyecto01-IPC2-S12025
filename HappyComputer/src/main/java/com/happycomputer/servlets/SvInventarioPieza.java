package com.happycomputer.servlets;

import com.happycomputer.modelos.InventarioPiezaModelo;
import com.happycomputer.modelos.PiezaModelo;
import com.happycomputer.persistenciadatos.InventarioPiezaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/SvInventarioPieza")
public class SvInventarioPieza  extends HttpServlet {
    private InventarioPiezaDAO inventarioPiezaDAO;

    @Override
    public void init() {
        inventarioPiezaDAO = new InventarioPiezaDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orden = request.getParameter("orden");
        if (orden == null) {
            orden = "ASC";
        }
        try{
            List<InventarioPiezaModelo> piezasAgotadas = inventarioPiezaDAO.orderBy(orden);
            request.setAttribute("piezasAgotadas", piezasAgotadas);
            request.getRequestDispatcher("/AREA_FABRICA/componentesAgotados.jsp").forward(request, response);
        }catch (SQLException e){
            throw new ServletException("Error al obtener las piezas agotadas", e);
        }
    }
}
