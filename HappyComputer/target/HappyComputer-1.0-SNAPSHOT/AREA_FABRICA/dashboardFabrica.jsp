<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.happycomputer.persistenciadatos.EnsamblarComputadoraDAO" %>
<%@ page import="com.happycomputer.modelos.EnsamblarComputadoraModelo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.happycomputer.persistenciadatos.PiezaDAO" %>
<%@ page import="com.happycomputer.modelos.PiezaModelo" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Area Ensamblaje</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 p-6">

<h1 class="text-3xl font-bold mb-4 text-center">Area Ensamblaje</h1>

<div class="container mx-auto bg-white shadow-md p-6 rounded-lg">
    <h2 class="text-xl font-semibold mb-4">Computadoras Ensambladas</h2>

    <table class="w-full border-collapse border border-gray-300">
        <thead>
        <tr class="bg-gray-200">
            <th class="border p-2">ID Computadora</th>
            <th class="border p-2">ID Usuario</th>
            <th class="border p-2">Fecha</th>
        </tr>
        </thead>
        <tbody>
        <%
            EnsamblarComputadoraDAO ensamblarDAO = new EnsamblarComputadoraDAO();
            List<EnsamblarComputadoraModelo> ensamblajes = ensamblarDAO.findAll();
            for (EnsamblarComputadoraModelo ensamblaje : ensamblajes) {
        %>
        <tr class="border">
            <td class="border p-2"><%= ensamblaje.getIdComputadora() %></td>
            <td class="border p-2"><%= ensamblaje.getIdUsuario() %></td>
            <td class="border p-2"><%= ensamblaje.getFechaEnsamble() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <h2 class="text-xl font-semibold mt-6">Agregar Ensamblaje</h2>
    <form action="SvEnsamble" method="post" class="mt-4">
        <input type="hidden" name="accion" value="agregarEnsamblaje">
        <input type="text" name="idComputadora" placeholder="ID Computadora" required class="border p-2 mr-2">
        <input type="text" name="idUsuario" placeholder="ID Usuario" required class="border p-2 mr-2">
        <input type="date" name="fecha" required class="border p-2 mr-2">
        <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">Agregar</button>
    </form>
</div>

<div class="container mx-auto bg-white shadow-md p-6 rounded-lg mt-6">
    <h2 class="text-xl font-semibold mb-4">Gestión de Piezas</h2>
    <table class="w-full border-collapse border border-gray-300">
        <thead>
        <tr class="bg-gray-200">
            <th class="border p-2">ID Pieza</th>
            <th class="border p-2">Nombre</th>
            <th class="border p-2">Costo</th>
        </tr>
        </thead>
        <tbody>
        <%
            PiezaDAO piezaDAO = new PiezaDAO();
            List<PiezaModelo> piezas = piezaDAO.findAll();
            for (PiezaModelo pieza : piezas) {
        %>
        <tr class="border">
            <td class="border p-2"><%= pieza.getId() %></td>
            <td class="border p-2"><%= pieza.getNombre() %></td>
            <td class="border p-2">$<%= pieza.getCosto() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <h2 class="text-xl font-semibold mt-6">Agregar Pieza</h2>
    <form action="SvEnsamble" method="post" class="mt-4">
        <input type="hidden" name="accion" value="agregarPieza">
        <input type="text" name="nombre" placeholder="Nombre de Pieza" required class="border p-2 mr-2">
        <input type="number" name="costo" step="0.01" placeholder="Costo" required class="border p-2 mr-2">
        <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded">Agregar</button>
    </form>
</div>

</body>
</html>
