package com.happycomputer.servlets;

import com.happycomputer.modelos.ComputadoraModelo;
import com.happycomputer.modelos.EnsamblePiezaModelo;
import com.happycomputer.modelos.InventarioPiezaModelo;
import com.happycomputer.modelos.PiezaModelo;
import com.happycomputer.persistenciadatos.ComputadoraDAO;
import com.happycomputer.persistenciadatos.EnsamblePiezaDAO;
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

@WebServlet("/SvSeleccionarComputadora")
public class SvSeleccionarComputadora extends HttpServlet {

    private ComputadoraDAO computadoraDAO;
    private EnsamblePiezaDAO ensamblePiezaDAO;
    private PiezaDAO piezaDAO;
    private InventarioPiezaDAO inventarioPiezaDAO;

    @Override
    public void init() {
        computadoraDAO = new ComputadoraDAO();
        ensamblePiezaDAO = new EnsamblePiezaDAO();
        piezaDAO = new PiezaDAO();
        inventarioPiezaDAO = new InventarioPiezaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener la lista de computadoras disponibles (estado = 1)
            List<ComputadoraModelo> computadoras = computadoraDAO.findByEstado(true);
            request.setAttribute("computadoras", computadoras);

            // Redirigir al JSP para seleccionar una computadora
            request.getRequestDispatcher("/AREA_FABRICA/seleccionarComputadora.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al obtener las computadoras disponibles", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idComputadora = Integer.parseInt(request.getParameter("idComputadora"));

        try {
            // Obtener las piezas necesarias para la computadora seleccionada
            List<EnsamblePiezaModelo> piezasNecesarias = ensamblePiezaDAO.findByComputadora(idComputadora);

            //Obtener la lista de piezas disponibles
            List<PiezaModelo> piezas = piezaDAO.findAll();

            // Obtener la computadora seleccionada
            ComputadoraModelo computadora = computadoraDAO.findById(idComputadora);
            // Obtener las piezas agotadas o a punto de agotarse (cantidad < 5)
            List<InventarioPiezaModelo> piezasAgotadas = inventarioPiezaDAO.findByCantidadLessThan(5);

            // Pasar los datos al JSP
            request.setAttribute("piezasNecesarias", piezasNecesarias);
            request.setAttribute("piezas", piezas);
            request.setAttribute("computadora", computadora);
            request.setAttribute("piezasAgotadas", piezasAgotadas);

            // Redirigir al JSP para mostrar las piezas necesarias
            request.getRequestDispatcher("/AREA_FABRICA/piezasEnsambleComputadora.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al obtener las piezas necesarias", e);
        }
    }
}