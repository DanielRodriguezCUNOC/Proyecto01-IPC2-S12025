package com.happycomputer.servlets;

import com.happycomputer.modelos.ComputadoraModelo;
import com.happycomputer.modelos.EnsamblePiezaModelo;
import com.happycomputer.modelos.PiezaModelo;
import com.happycomputer.modelos.UsuarioModelo;
import com.happycomputer.persistenciadatos.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/SvCrearComputadora")
public class SvCrearComputadora extends HttpServlet {
    private ComputadoraDAO computadoraDAO;
    private EnsamblePiezaDAO ensamblePiezaDAO;
    private PiezaDAO piezaDAO;
    private EnsamblarComputadoraDAO ensamblarComputadoraDAO;
    private InventarioComputadoraDAO inventarioComputadoraDAO;
    @Override
    public void  init(){
        computadoraDAO = new ComputadoraDAO();
        ensamblePiezaDAO = new EnsamblePiezaDAO();
        piezaDAO = new PiezaDAO();
        ensamblarComputadoraDAO = new EnsamblarComputadoraDAO();
        inventarioComputadoraDAO = new InventarioComputadoraDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener la lista de piezas disponibles
        try {
            List<PiezaModelo> piezas = piezaDAO.findAll();
            request.setAttribute("piezas", piezas);
            request.getRequestDispatcher("/AREA_FABRICA/crearComputadora.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al obtener las piezas", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //* Obtener al usuario de la sesion
        HttpSession session = request.getSession();
        UsuarioModelo usuario = (UsuarioModelo) session.getAttribute("usuario");
        if (usuario == null || usuario.getIdRol() != 3) {
             response.sendRedirect("login.jsp");
             return;
         }
        int idUsuario = usuario.getId();

        String nombreComputadora = request.getParameter("nombreComputadora");
        double precioVenta = Double.parseDouble(request.getParameter("precioVenta"));
        String[] piezasIds = request.getParameterValues("piezas");
        String[] cantidades = request.getParameterValues("cantidades");

        try{
            if(computadoraDAO.findByNombre(nombreComputadora)){
                request.setAttribute("error", "Ya existe una computadora con ese nombre");
                request.getRequestDispatcher("/AREA_FABRICA/ensamblarComputadora.jsp").forward(request, response);
                return;
            }
            //* Crear la computadora en la tabla Computadora
            ComputadoraModelo computadora = new ComputadoraModelo(null, nombreComputadora, precioVenta);
            int idComputadora = computadoraDAO.insert(computadora).getId();

            // Insertar las piezas en la tabla Ensamble_Pieza
            if (piezasIds != null && cantidades != null) {
                for (int i = 0; i < piezasIds.length; i++) {
                    int idPieza = Integer.parseInt(piezasIds[i]);
                    int cantidad = Integer.parseInt(cantidades[i]);

                    // Crear un objeto EnsamblePiezaModelo
                    EnsamblePiezaModelo ensamblePieza = new EnsamblePiezaModelo(null, idComputadora, idPieza, cantidad);
                    // Insertar en Ensamble_Pieza
                    ensamblePiezaDAO.insert(ensamblePieza);
                }
            }


            response.sendRedirect("SvEnsamblaje");
        }catch (SQLException e){
            throw new ServletException("Error al insertar la computadora", e);
        }
    }
}
