package com.happycomputer.servlets.areaventas;

import com.happycomputer.dto.DetalleComputadoraDTO;
import com.happycomputer.dto.VentaDTO;
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

@WebServlet("/SvVenta")
public class SvVenta extends HttpServlet {
    private VentaDAO ventaDAO;
    private ClienteDAO clienteDAO;
    private DetalleVentaDAO detalleVentaDAO;
    private InventarioComputadoraDAO inventarioComputadoraDAO;
    private ComputadoraDAO computadoraDAO;

    @Override
    public void init() {
        ventaDAO = new VentaDAO();
        clienteDAO = new ClienteDAO();
        detalleVentaDAO = new DetalleVentaDAO();
        inventarioComputadoraDAO = new InventarioComputadoraDAO();
        computadoraDAO = new ComputadoraDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("ventaDelDia".equals(action)) {
            try {
                List<VentaDTO> ventas = ventaDAO.findSalesByDate();
                request.setAttribute("ventas", ventas);
                request.getRequestDispatcher("/AREA_VENTAS/ventasDelDia.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Error al obtener las ventas", e);
            }
        } else {
            try {
                //Obtener las computadoras disponibles
                List<DetalleComputadoraDTO> computadoras = inventarioComputadoraDAO.getByNameAndPrice();
                //Enviar las computadoras a la vista
                request.setAttribute("computadoras", computadoras);
                request.getRequestDispatcher("/AREA_VENTAS/Venta.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Error al obtener las computadoras", e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nit = request.getParameter("nit");
        String[] computadoras = request.getParameterValues("computadoras");

        try {
            ClienteModelo cliente = clienteDAO.findByNit(nit);
            if (cliente == null) {
                //Almacenamos el error
                HttpSession sessionError = request.getSession();
                sessionError.setAttribute("error", "El cliente no existe");
                response.sendRedirect(request.getContextPath() + "/AREA_VENTAS/Venta.jsp");
                return;
            }
            VentaModelo ventaModelo = new VentaModelo(
                    null, (Integer) request.getSession().getAttribute("idUsuario"), cliente.getNit(), calcularTotal(computadoras), new Date()
            );
            ventaModelo = ventaDAO.insert(ventaModelo);
            int idVenta = ventaModelo.getId();
            //Registrar los detalles de la venta
            for (String idComputadora : computadoras) {
                ComputadoraModelo computadora = ventaDAO.findComputadoraById(Integer.parseInt(idComputadora));
                DetalleVentaModelo detalleVenta = new DetalleVentaModelo(
                        null,
                        idVenta,
                        computadora.getId(),
                        computadora.getPrecioVenta()
                );
                detalleVentaDAO.insert(detalleVenta);
            }

            //Generar la factura
            generarFactura(idVenta, cliente, computadoras, ventaModelo.getTotalVenta());
            response.sendRedirect(request.getContextPath() + "/AREA_VENTAS/facturaPDF.jsp?idVenta=" + idVenta);
        } catch (Exception e) {
            throw new ServletException("Error al realizar la venta", e);
        }
    }

    private double calcularTotal(String[] computadoras) throws SQLException {
        double total = 0;
        for (String idComputadora : computadoras) {
            ComputadoraDAO computadoraDAO = new ComputadoraDAO();
            ComputadoraModelo computadora = computadoraDAO.findComputadoraByName(idComputadora);
            total += computadora.getPrecioVenta();
        }


        return total;
    }

    private void generarFactura(int idVenta, ClienteModelo cliente, String[] computadoras, double total) {
        //Generar la factura

    }
}
