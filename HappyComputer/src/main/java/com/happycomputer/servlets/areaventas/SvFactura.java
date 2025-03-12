package com.happycomputer.servlets.areaventas;

import com.happycomputer.dto.DetalleVentaDTO;
import com.happycomputer.modelos.VentaModelo;
import com.happycomputer.persistenciadatos.DetalleVentaDAO;
import com.happycomputer.persistenciadatos.VentaDAO;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet("/SvFactura")
public class SvFactura extends HttpServlet {
    private VentaDAO ventaDAO;
    private DetalleVentaDAO detalleVentaDAO;

    @Override
    public void init() {
        ventaDAO = new VentaDAO();
        detalleVentaDAO = new DetalleVentaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("detalleFactura".equals(action)) {
            try {
                int idVenta = Integer.parseInt(request.getParameter("idVenta"));
                VentaModelo venta = ventaDAO.findById(idVenta);
                List<DetalleVentaDTO> detalles = detalleVentaDAO.findDetailsBySaleId(idVenta);
                request.setAttribute("venta", venta);
                request.setAttribute("detalles", detalles);
                request.getRequestDispatcher("/AREA_VENTAS/mostrarDetalleFactura.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Error al obtener las ventas", e);
            }

        } else if ("listarVentas".equals(action)) {
            try {
                List<VentaModelo> ventas = ventaDAO.findAll();
                request.setAttribute("ventas", ventas);
                request.getRequestDispatcher("/AREA_VENTAS/listarVentas.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Error al obtener las ventas", e);
            }

        } else {
            int idVenta = Integer.parseInt(request.getParameter("idVenta"));
            try {
                VentaModelo venta = ventaDAO.findById(idVenta);
                List<DetalleVentaDTO> detalles = detalleVentaDAO.findDetailsBySaleId(idVenta);
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition",
                        "attachment; filename=factura_" + idVenta + ".pdf");

                try (OutputStream out = response.getOutputStream()) {
                    PdfWriter writer = new PdfWriter(out);
                    PdfDocument pdf = new PdfDocument(writer);
                    Document document = new Document(pdf);

                    document.add(new Paragraph("Factura"));
                    document.add(new Paragraph("ID Venta: " + venta.getId()));
                    document.add(new Paragraph("Fecha: " + venta.getFechaVenta()));
                    document.add(new Paragraph("Total: " + venta.getTotalVenta()));

                    for (DetalleVentaDTO detalle : detalles) {
                        document.add(new Paragraph("Computadora: " + detalle.getNombreComputadora()));
                        document.add(new Paragraph("Precio Unitario: " + detalle.getPrecioUnidad()));
                    }
                    document.close();
                }
            } catch (Exception e) {
                throw new ServletException("Error al obtener las ventas", e);
            }
        }
    }
}
