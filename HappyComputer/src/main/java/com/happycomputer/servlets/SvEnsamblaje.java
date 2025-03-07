package com.happycomputer.servlets;

import com.happycomputer.modelos.*;
import com.happycomputer.persistenciadatos.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet("/SvEnsamblaje")
public class SvEnsamblaje extends HttpServlet {
    private ComputadoraDAO computadoraDAO;
    private EnsamblePiezaDAO ensamblePiezaDAO;
    private PiezaDAO piezaDAO;
    private EnsamblarComputadoraDAO ensamblarComputadoraDAO;
    private InventarioComputadoraDAO inventarioComputadoraDAO;

    @Override
    public void init() {
        computadoraDAO = new ComputadoraDAO();
        ensamblePiezaDAO = new EnsamblePiezaDAO();
        piezaDAO = new PiezaDAO();
        ensamblarComputadoraDAO = new EnsamblarComputadoraDAO();
        inventarioComputadoraDAO = new InventarioComputadoraDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orden = request.getParameter("orden");
        if (orden == null) {
            orden = "ASC";
        }
        try {
            List<EnsamblarComputadoraModelo> computadoras = ensamblarComputadoraDAO.findByDate(orden);
            request.setAttribute("computadoras", computadoras);
            request.getRequestDispatcher("/AREA_FABRICA/listarComputadoras.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al obtener las computadoras ensambladas", e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //* Obtener al usuario de la sesion
        HttpSession session = request.getSession();
        UsuarioModelo usuario = (UsuarioModelo) session.getAttribute("usuario");
       /* if (usuario == null || usuario.getIdRol() != 1) {
            response.sendRedirect("login.jsp");
            return;
        }*/
        //int idUsuario = usuario.getId();
        int idUsuario = 1;

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
            //* Calcular el costo de la computadora
            double costoEnsamble = 0.0;
            for (int i = 0; i < piezasIds.length; i++) {
                PiezaModelo pieza = piezaDAO.findById(Integer.parseInt(piezasIds[i]));
                costoEnsamble += pieza.getCosto() * Integer.parseInt(cantidades[i]);
            }
            //* Crear la computadora en la tabla Computadora
            ComputadoraModelo computadora = new ComputadoraModelo(null, nombreComputadora, precioVenta);
            computadoraDAO.insert(computadora);
            //* Crear la computadora ensamblada en la tabla Ensamblar_Computadora
            EnsamblarComputadoraModelo ensamblarComputadora = new EnsamblarComputadoraModelo(null, computadora.getId(), idUsuario, new Date(), costoEnsamble);
            ensamblarComputadoraDAO.insert(ensamblarComputadora);
            //* Registrar la computadora en el inventario
            InventarioComputadoraModelo inventarioComputadora = new InventarioComputadoraModelo(null, ensamblarComputadora.getId(), 1);
            inventarioComputadoraDAO.insert(inventarioComputadora);
            for (int i = 0; i < piezasIds.length; i++) {
                EnsamblePiezaModelo ensamblePieza = new EnsamblePiezaModelo(null, computadora.getId(), Integer.parseInt(piezasIds[i]), Integer.parseInt(cantidades[i]));
                ensamblePiezaDAO.insert(ensamblePieza);
            }
            response.sendRedirect("SvEnsamblaje");
        }catch (SQLException e){
            throw new ServletException("Error al insertar la computadora", e);
        }
    }
}
