package com.happycomputer.servlets.areaventas;

import com.happycomputer.modelos.ClienteModelo;
import com.happycomputer.modelos.ComputadoraModelo;
import com.happycomputer.modelos.DetalleVentaModelo;
import com.happycomputer.modelos.VentaModelo;
import com.happycomputer.persistenciadatos.ClienteDAO;
import com.happycomputer.persistenciadatos.DetalleVentaDAO;
import com.happycomputer.persistenciadatos.VentaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/SvVenta")
public class SvVenta extends HttpServlet {
    private VentaDAO ventaDAO;
    private ClienteDAO clienteDAO;
    private DetalleVentaDAO detalleVentaDAO;

    @Override
    public void init() {
        ventaDAO = new VentaDAO();
        clienteDAO = new ClienteDAO();
        detalleVentaDAO = new DetalleVentaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nit = request.getParameter("nit");
        String[] computadoras = request.getParameterValues("computadoras");

        try {
            ClienteModelo cliente = clienteDAO.findByNit(nit);
            if (cliente == null) {
                request.setAttribute("error", "El cliente no existe");
                request.getRequestDispatcher("/AREA_VENTAS/registrarCliente.jsp").forward(request, response);
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
            ComputadoraModelo computadora = ventaDAO.findComputadoraById(Integer.parseInt(idComputadora));
            total += computadora.getPrecioVenta();
        }


        return total;
    }

    private void generarFactura(int idVenta, ClienteModelo cliente, String[] computadoras, double total) {
        //Generar la factura

    }
}
