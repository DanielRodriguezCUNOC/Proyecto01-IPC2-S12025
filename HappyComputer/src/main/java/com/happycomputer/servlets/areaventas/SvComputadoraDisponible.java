package com.happycomputer.servlets.areaventas;

import com.happycomputer.dto.DetalleComputadoraDTO;
import com.happycomputer.modelos.ComputadoraModelo;
import com.happycomputer.modelos.InventarioComputadoraModelo;
import com.happycomputer.persistenciadatos.ComputadoraDAO;
import com.happycomputer.persistenciadatos.InventarioComputadoraDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/SvComputadoraDisponible")
public class SvComputadoraDisponible extends HttpServlet {
    private InventarioComputadoraDAO inventarioComputadoraDAO;
    private ComputadoraDAO computadoraDAO;

    @Override
    public void init() {
        inventarioComputadoraDAO = new InventarioComputadoraDAO();
        computadoraDAO = new ComputadoraDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener la lista de computadoras disponibles (estado = 1)
            List<DetalleComputadoraDTO> computadoras = inventarioComputadoraDAO.getByNameAndPrice();
            request.setAttribute("computadoras", computadoras);

            // Redirigir al JSP para seleccionar una computadora
            request.getRequestDispatcher("/AREA_FABRICA/consultaComputadoras.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al obtener las computadoras disponibles", e);
        }
    }
}
