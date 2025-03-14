package com.happycomputer.servlets;

import com.happycomputer.modelos.UsuarioModelo;
import com.happycomputer.persistenciadatos.UsuarioDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author luluwalilith
 */
@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");

        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            UsuarioModelo user = usuarioDAO.findByUsuario(usuario);
            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", user);
                String urlRedirect = "index.jsp";
                if (user.getIdRol().equals(1)) {
                    response.sendRedirect(request.getContextPath() + "/AREA_FABRICA/dashboardFabrica.jsp");
                } else if (user.getIdRol().equals(2)) {
                    response.sendRedirect(request.getContextPath() + "/AREA_VENTAS/dashboardVentas.jsp");

                } else if (user.getIdRol().equals(3)) {
                    response.sendRedirect(request.getContextPath() + "/AREA_ADMIN/dashboardAdministracion.jsp");
                }
            } else {
                request.setAttribute("error", "usuario o password incorretos");
                response.sendRedirect(request.getContextPath() + "/index.jsp?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

