package com.happycomputer.servlets;

import com.happycomputer.modelos.InventarioPiezaModelo;
import com.happycomputer.modelos.PiezaModelo;
import com.happycomputer.persistenciadatos.InventarioPiezaDAO;
import com.happycomputer.persistenciadatos.PiezaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/SvPieza")
public class SvPieza extends HttpServlet {
    private PiezaDAO piezaDAO;

    @Override
    public void init() {
        piezaDAO = new PiezaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if("verificar".equals(action)){
            String nombre = request.getParameter("nombre");
            try{
               boolean existe = piezaDAO.existePieza(nombre);
               response.setContentType("application/json");
               response.setCharacterEncoding("UTF-8");
               response.getWriter().write("{\"existe\": " + existe + "}");
            }catch (SQLException e){
                throw new ServletException("Error al verificar la pieza", e);
            }

        }else if ("editar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                PiezaModelo pieza = piezaDAO.findById(id);
                request.setAttribute("pieza", pieza);
                request.getRequestDispatcher("/AREA_FABRICA/editarPieza.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Error al obtener la pieza", e);
            }
        } else {
            try{
                List<PiezaModelo> piezas = piezaDAO.findAll();
                request.setAttribute("piezas", piezas);
                request.getRequestDispatcher("/AREA_FABRICA/piezas.jsp").forward(request, response);
            }catch (SQLException e) {
                throw new ServletException("Error al obtener las piezas", e);
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("crear".equals(action)) {
            String nombre = request.getParameter("nombre");
            double costo = Double.parseDouble(request.getParameter("costo"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));

            PiezaModelo pieza = new PiezaModelo(null, nombre, costo);
            InventarioPiezaDAO inventarioPiezaDAO = new InventarioPiezaDAO();
            try{
               piezaDAO.insert(pieza);
                System.out.println("Pieza insertada" + pieza);
                InventarioPiezaModelo inventarioPieza = new InventarioPiezaModelo(null, pieza.getId(), cantidad);
                inventarioPiezaDAO.insert(inventarioPieza);
                System.out.println("Inventario insertado" + inventarioPieza);
            }catch (SQLException e){
                throw new ServletException("Error al insertar la pieza", e);
            }
        } else if("eliminar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            try{
                piezaDAO.cambiarEstado(id, false);
        }catch (SQLException e){
            throw new ServletException("Error al eliminar la pieza", e);
        }
        } else if ("actualizar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            double costo = Double.parseDouble(request.getParameter("costo"));

            PiezaModelo pieza = new PiezaModelo(null, nombre, costo);
            try{
                piezaDAO.update(pieza);
            } catch (SQLException e) {
                throw new ServletException("Error al actualizar la pieza", e);
            }
        }
        response.sendRedirect(request.getContextPath() + "/SvPieza");
    }
}
