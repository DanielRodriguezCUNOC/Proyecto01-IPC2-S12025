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
    String action = request.getParameter("action");
    if("ensamblar".equals(action)) {

        try{
            List<PiezaModelo> piezas = piezaDAO.findAll();
            request.setAttribute("piezas", piezas);
            request.getRequestDispatcher("/AREA_FABRICA/ensamblarComputadora.jsp").forward(request, response);
        }catch (SQLException e) {
            throw new ServletException("Error al obtener las piezas", e);
        }
    }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //* Obtener al usuario de la sesion
        HttpSession session = request.getSession();
        UsuarioModelo usuario = (UsuarioModelo) session.getAttribute("usuario");
       if (usuario == null || usuario.getIdRol() != 1) {
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
            //* Calcular el costo de la computadora
            double costoEnsamble = 0.0;
            for (int i = 0; i < piezasIds.length; i++) {
                PiezaModelo pieza = piezaDAO.findById(Integer.parseInt(piezasIds[i]));
                costoEnsamble += pieza.getCosto() * Integer.parseInt(cantidades[i]);
            }
            //* Crear la computadora en la tabla Computadora
            ComputadoraModelo computadora = new ComputadoraModelo(null, nombreComputadora, precioVenta);
            computadoraDAO.insert(computadora);
            System.out.println("Computadora: " + computadora.getId());
            //* Crear la computadora ensamblada en la tabla Ensamblar_Computadora
            EnsamblarComputadoraModelo ensamblarComputadora = new EnsamblarComputadoraModelo(null, computadora.getId(), idUsuario, new Date(), costoEnsamble);
            ensamblarComputadoraDAO.insert(ensamblarComputadora);
            System.out.println("Computadora ensamblada: " + ensamblarComputadora.getId());
            //* Registrar la computadora en el inventario
            InventarioComputadoraModelo inventarioComputadora = new InventarioComputadoraModelo(null, ensamblarComputadora.getId(), 1);
            inventarioComputadoraDAO.insert(inventarioComputadora);
            System.out.println("Inventario computadora: " + inventarioComputadora.getId());
            for (int i = 0; i < piezasIds.length; i++) {
                EnsamblePiezaModelo ensamblePieza = new EnsamblePiezaModelo(null, computadora.getId(), Integer.parseInt(piezasIds[i]), Integer.parseInt(cantidades[i]));
                ensamblePiezaDAO.insert(ensamblePieza);
            }
            response.sendRedirect(request.getContextPath()+"/ensamblarComputadora.jsp");
        }catch (SQLException e){
            throw new ServletException("Error al insertar la computadora", e);
        }
    }
}
