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
       String action = request.getParameter("action");
       if("agotadas".equals(action)){
           String orden = request.getParameter("orden");
           if(orden != null){
               orden = "ASC";
           }
           try{
                List<InventarioPiezaModelo> piezasAgotadas = inventarioPiezaDAO.orderBy(orden);
                request.setAttribute("piezasAgotadas", piezasAgotadas);
                request.getRequestDispatcher("/AREA_FABRICA/componentesAgotados.jsp").forward(request, response);
           }catch (SQLException e) {
                throw new ServletException("Error al obtener las piezas agotadas", e);
           }
       }else{
           try{
                List<InventarioPiezaModelo> inventarioPiezas = inventarioPiezaDAO.findAll();
                request.setAttribute("inventarioPiezas", inventarioPiezas);
                request.getRequestDispatcher("/AREA_FABRICA/agregarPiezaInventario.jsp").forward(request, response);
           }catch (SQLException e) {
                throw new ServletException("Error al obtener el inventario de piezas", e);
           }
       }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
         if ("agregarInventario".equals(action)) {
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
        }
    }
}
