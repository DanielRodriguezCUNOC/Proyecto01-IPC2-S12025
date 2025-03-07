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
               response.setContentType("apllication/json");
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
                String origen = request.getParameter("origen");
                System.out.println("Origen: " + origen);
                if ("ensamblar".equals(origen)) {
                    System.out.println("Redirigiendo a ensamblarComputadora.jsp");
                    request.getRequestDispatcher("/AREA_FABRICA/ensamblarComputadora.jsp").forward(request, response);

                } else {
                    System.out.println("Redirigiendo a piezas.jsp");
                    request.getRequestDispatcher("/AREA_FABRICA/piezas.jsp").forward(request, response);
                }
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
        } else if ("agregarInventario".equals(action)) {
            int idPieza = Integer.parseInt(request.getParameter("idPieza"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            InventarioPiezaDAO inventarioPiezaDAO = new InventarioPiezaDAO();

            try{
                InventarioPiezaModelo inventarioPieza = inventarioPiezaDAO.findByIdPieza(idPieza);
                if(inventarioPieza != null){
                    int nuevaCantidad = inventarioPieza.getCantidad() + cantidad;
                    inventarioPieza.setCantidad(nuevaCantidad);
                    inventarioPiezaDAO.update(inventarioPieza);
                    System.out.println("Inventario actualizado" + inventarioPieza);
                }else{
                    inventarioPieza = new InventarioPiezaModelo(null, idPieza, cantidad);
                    inventarioPiezaDAO.insert(inventarioPieza);
                    System.out.println("Inventario insertado" + inventarioPieza);
                }
            }catch (SQLException e){
                throw new ServletException("Error al agregar inventario", e);
            }
        } else if("eliminar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            try{
                piezaDAO.delete(id);
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
