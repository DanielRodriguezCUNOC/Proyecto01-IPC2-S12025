<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.happycomputer.persistenciadatos.InventarioPiezaDAO" %>
<%@ page import="com.happycomputer.persistenciadatos.InventarioComputadoraDAO" %>
<%@ page import="com.happycomputer.modelos.InventarioPiezaModelo" %>
<%@ page import="com.happycomputer.modelos.InventarioComputadoraModelo" %>
<%@ page import="com.happycomputer.persistenciadatos.EnsamblarComputadoraDAO" %>
<%@ page import="com.happycomputer.modelos.EnsamblarComputadoraModelo" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Fábrica</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Área de Ensamblaje</h1>

<h2>Inventario de Piezas</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Pieza</th>
        <th>Cantidad</th>
    </tr>
    </thead>
    <tbody>
    <%
        InventarioPiezaDAO piezaDAO = new InventarioPiezaDAO();
        List<InventarioPiezaModelo> piezas = piezaDAO.findAll();
        for (InventarioPiezaModelo pieza : piezas) {
    %>
    <tr>
        <td><%= pieza.getId() %></td>
        <td><%= pieza.getIdPieza() %></td>
        <td><%= pieza.getCantidad() %></td>
    </tr>
    <% } %>
    </tbody>
</table>

<h2>Inventario de Computadoras Ensambladas</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Computadora</th>
        <th>Cantidad</th>
    </tr>
    </thead>
    <tbody>
    <%
        InventarioComputadoraDAO computadoraDAO = new InventarioComputadoraDAO();
        List<InventarioComputadoraModelo> computadoras = computadoraDAO.findAll();
        for (InventarioComputadoraModelo computadora : computadoras) {
    %>
    <tr>
        <td><%= computadora.getId() %></td>
        <td><%= computadora.getIdEnsamblarComputadora() %></td>
        <td><%= computadora.getCantidad() %></td>
    </tr>
    <% } %>
    </tbody>
</table>

<h2>Computadoras Ensambladas</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Computadora</th>
        <th>Usuario</th>
        <th>Costo Ensamblaje</th>
        <th>Fecha</th>
    </tr>
    </thead>
    <tbody>
    <%
        EnsamblarComputadoraDAO ensamblarDAO = new EnsamblarComputadoraDAO();
        List<EnsamblarComputadoraModelo> ensamblajes = ensamblarDAO.findAll();
        for (EnsamblarComputadoraModelo ensamblaje : ensamblajes) {
    %>
    <tr>
        <td><%= ensamblaje.getId() %></td>
        <td><%= ensamblaje.getIdComputadora() %></td>
        <td><%= ensamblaje.getIdUsuario() %></td>
        <td><%= ensamblaje.getCostoEnsamble() %></td>
        <td><%= ensamblaje.getFechaEnsamble() %></td>
    </tr>
    <% } %>
    </tbody>
</table>

<h2>Alertas de Inventario</h2>
<ul>
    <%
        for (InventarioPiezaModelo pieza : piezas) {
            if (pieza.getCantidad() == 0) {
    %>
    <li style="color: red;">La pieza con ID <%= pieza.getIdPieza() %> está agotada.</li>
    <%
    } else if (pieza.getCantidad() < 5) {
    %>
    <li style="color: orange;">La pieza con ID <%= pieza.getIdPieza() %> está a punto de agotarse (Stock: <%= pieza.getCantidad() %>).</li>
    <%
            }
        }
    %>
</ul>
</body>
</html>
