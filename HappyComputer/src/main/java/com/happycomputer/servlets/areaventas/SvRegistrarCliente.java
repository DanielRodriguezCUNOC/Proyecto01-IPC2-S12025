package com.happycomputer.servlets.areaventas;


import com.happycomputer.modelos.ClienteModelo;
import com.happycomputer.persistenciadatos.ClienteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/SvRegistrarCliente")
public class SvRegistrarCliente extends HttpServlet {
    private ClienteDAO clienteDAO;

    @Override
    public void init() {
        clienteDAO = new ClienteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nit = request.getParameter("nit");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        try {
            clienteDAO.insert(new ClienteModelo(nit, nombre, direccion));
            response.sendRedirect(request.getContextPath() + "/SvVenta?action");
        } catch (SQLException e) {
            throw new ServletException("Error al registrar el cliente", e);
        }
    }
}
