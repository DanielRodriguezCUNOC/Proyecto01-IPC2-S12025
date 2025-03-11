package com.happycomputer.servlets.areaensamblaje;

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
    private InventarioPiezaDAO inventarioPiezaDAO;

    @Override
    public void init() {
        computadoraDAO = new ComputadoraDAO();
        ensamblePiezaDAO = new EnsamblePiezaDAO();
        piezaDAO = new PiezaDAO();
        ensamblarComputadoraDAO = new EnsamblarComputadoraDAO();
        inventarioComputadoraDAO = new InventarioComputadoraDAO();
        inventarioPiezaDAO = new InventarioPiezaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        int idComputadora = Integer.parseInt(request.getParameter("idComputadora"));

        try {
            ComputadoraModelo computadoraModelo = computadoraDAO.findById(idComputadora);
            if (computadoraModelo == null) {
                request.setAttribute("error", "El computadora no existe");
                request.getRequestDispatcher("/AREA_FABRICA/seleccionarComputadora.jsp").forward(request, response);
                return;
            }
            List<EnsamblePiezaModelo> piezasNecesarias = ensamblePiezaDAO.findByComputadora(idComputadora);

            //* Verificamos que hayan suficientes piezas en el inventario
            for (EnsamblePiezaModelo ensamblePieza : piezasNecesarias) {
                try {
                    InventarioPiezaModelo inventarioPieza = inventarioPiezaDAO.findByIdPieza(ensamblePieza.getIdPieza());
                    if (inventarioPieza == null || inventarioPieza.getCantidad() < ensamblePieza.getCantidad()) {
                        // Obtener el nombre de la pieza usando PiezaDAO
                        PiezaModelo pieza = piezaDAO.findById(ensamblePieza.getIdPieza());
                        String nombrePieza = (pieza != null) ? pieza.getNombre() : "Pieza Desconocida";

                        request.setAttribute("error", "No hay suficientes piezas en el inventario para " + nombrePieza);
                        request.getRequestDispatcher("/AREA_FABRICA/seleccionarPiezas.jsp").forward(request, response);
                        return;
                    }
                } catch (SQLException e) {
                    throw new ServletException("Error al obtener el inventario de piezas", e);
                }
            }

            // Reducir la cantidad de piezas en el inventario
            for (EnsamblePiezaModelo ensamblePieza : piezasNecesarias) {
                InventarioPiezaModelo inventarioPieza = inventarioPiezaDAO.findByIdPieza(ensamblePieza.getIdPieza());
                int nuevaCantidad = inventarioPieza.getCantidad() - ensamblePieza.getCantidad();
                inventarioPieza.setCantidad(nuevaCantidad);
                inventarioPiezaDAO.update(inventarioPieza);
            }

            //* Crear la computadora ensamblada en la tabla Ensamblar_Computadora
            double costoEnsamble = calcularCostoEnsamble(piezasNecesarias);
            EnsamblarComputadoraModelo ensamblarComputadora = new EnsamblarComputadoraModelo(null, idComputadora, idUsuario, new Date(), costoEnsamble);
            ensamblarComputadoraDAO.insert(ensamblarComputadora);
            System.out.println("Computadora ensamblada: " + ensamblarComputadora.getId());
            //* Registrar la computadora en el inventario
            InventarioComputadoraModelo inventarioComputadora = new InventarioComputadoraModelo(null, ensamblarComputadora.getId(), 1);
            inventarioComputadoraDAO.insert(inventarioComputadora);
            System.out.println("Inventario computadora: " + inventarioComputadora.getId());
            response.sendRedirect(request.getContextPath() + "/listarComputadoras.jsp");
        } catch (SQLException e) {
            throw new ServletException("Error al insertar la computadora", e);
        }
    }

    private double calcularCostoEnsamble(List<EnsamblePiezaModelo> piezasNecesarias) throws SQLException {
        double costoEnsamble = 0.0;
        for (EnsamblePiezaModelo ensamblePieza : piezasNecesarias) {
            PiezaModelo pieza = piezaDAO.findById(ensamblePieza.getIdPieza());
            costoEnsamble += pieza.getCosto() * ensamblePieza.getCantidad();
        }
        return costoEnsamble;
    }
}
