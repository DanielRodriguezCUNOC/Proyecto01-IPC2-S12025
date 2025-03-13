package com.happycomputer.servlets.administracion;

import com.happycomputer.modelos.UsuarioModelo;
import com.happycomputer.persistenciadatos.UsuarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SvObtenerUsuarios")
public class SvObtenerUsuarios extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<UsuarioModelo> usuarios;
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarios = usuarioDAO.findAll();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/AREA_ADMIN/listaUsuarios.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Error al obtener los usuarios", e);
        }
    }
}
