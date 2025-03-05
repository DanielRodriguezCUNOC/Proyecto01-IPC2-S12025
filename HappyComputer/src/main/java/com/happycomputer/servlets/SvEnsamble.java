package com.happycomputer.servlets;

import com.happycomputer.modelos.EnsamblarComputadoraModelo;
import com.happycomputer.persistenciadatos.EnsamblarComputadoraDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SvEnsamble", urlPatterns = {"/SvEnsamble"})
public class SvEnsamble extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EnsamblarComputadoraDAO eCDAO;
    @Override
    public void init() throws ServletException {
        eCDAO = new EnsamblarComputadoraDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            List<EnsamblarComputadoraModelo> ensambles = eCDAO.findAll();
            request.setAttribute("ensambles", ensambles);
            request.getRequestDispatcher("/AREA_FABRICA/dashboardFabrica.jsp").forward(request, response);
        }catch (SQLException e){
            throw new ServletException("Error al obtener las computadoras ensambladas",e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try{
            if ("create".equals(action)) {
                crearEnsamble(request, response);
            } else if ("delete".equals(action)) {
                eliminarEnsamble(request, response);
            }else{
                response.sendRedirect("AREA_FABRICA/dashboardFabrica.jsp");
            }
        }catch (SQLException e){
            throw new ServletException("Error al conectar a la base de datos",e);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void crearEnsamble(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
      Integer idComputadora = Integer.parseInt(request.getParameter("idComputadora"));
        Integer idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        Double costoEnsamble = Double.parseDouble(request.getParameter("costoEnsamble"));
        Date fechaEnsable = new Date();

         EnsamblarComputadoraModelo nuevoEnsamble = new EnsamblarComputadoraModelo(null, idComputadora, idUsuario, costoEnsamble, fechaEnsable);
         eCDAO.insert(nuevoEnsamble);
            response.sendRedirect("SvEnsamble");

    }

    private void eliminarEnsamble(HttpServletRequest request, HttpServletResponse response){
        Integer id = Integer.parseInt(request.getParameter("id"));
        try{
            eCDAO.delete(id);
            response.sendRedirect("SvEnsamble");
        }catch (SQLException | IOException e){
            e.printStackTrace();
        }
    }
}
