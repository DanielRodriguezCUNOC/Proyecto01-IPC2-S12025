<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.happycomputer.persistenciadatos.InventarioPiezaDAO" %>
<%@ page import="com.happycomputer.persistenciadatos.InventarioComputadoraDAO" %>
<%@ page import="com.happycomputer.modelos.InventarioPiezaModelo" %>
<%@ page import="com.happycomputer.modelos.InventarioComputadoraModelo" %>
<%@ page import="com.happycomputer.modelos.UsuarioModelo" %>
<%@ page import="com.happycomputer.persistenciadatos.PiezaDAO" %>
<%@ page import="com.happycomputer.persistenciadatos.UsuarioDAO" %>
<%@ page import="java.sql.SQLException" %>
<%
    PiezaDAO piezaDAO = new PiezaDAO();
    InventarioPiezaDAO iPiezaDAO = new InventarioPiezaDAO();
    InventarioComputadoraDAO computadoraDAO = new InventarioComputadoraDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    List<InventarioPiezaModelo> piezas = null;
    List<InventarioComputadoraModelo> computadoras = null;
    List<UsuarioModelo> ensambladores = null;

    try {
        piezas = iPiezaDAO.findAll();
        computadoras = computadoraDAO.findAll();
        ensambladores = usuarioDAO.findByRol(1);
    } catch (SQLException e) {
        e.printStackTrace();
        out.println("Error al obtener datos: " + e.getMessage());
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Dashboard Fábrica</title>
</head>
<body class="bg-gray-100 p-6">
<div class="container mx-auto bg-white p-6 rounded-lg shadow-md">
    <h1 class="text-2xl font-bold mb-4">Dashboard Área de Ensamblaje</h1>

    <!-- Tabla de Inventario de Piezas -->
    <h2 class="text-xl font-semibold mt-4">Inventario de Piezas</h2>
    <table class="w-full mt-2 border-collapse border border-gray-300">
        <thead>
        <tr class="bg-gray-200">
            <th class="border p-2">ID</th>
            <th class="border p-2">ID Pieza</th>
            <th class="border p-2">Cantidad</th>
            <th class="border p-2">Acciones</th>
        </tr>
        </thead>
        <tbody>
        <% if (piezas != null) { %>
        <% for (InventarioPiezaModelo pieza : piezas) { %>
        <tr>
            <td class="border p-2"><%= pieza.getId() %></td>
            <td class="border p-2"><%= pieza.getIdPieza() %></td>
            <td class="border p-2"><%= pieza.getCantidad() %></td>
            <td class="border p-2">
                <button class="bg-blue-500 text-white px-2 py-1 rounded">Editar</button>
                <button class="bg-red-500 text-white px-2 py-1 rounded">Eliminar</button>
            </td>
        </tr>
        <% } %>
        <% } %>
        </tbody>
    </table>

    <!-- Tabla de Computadoras Ensambladas -->
    <h2 class="text-xl font-semibold mt-6">Computadoras Ensambladas</h2>
    <table class="w-full mt-2 border-collapse border border-gray-300">
        <thead>
        <tr class="bg-gray-200">
            <th class="border p-2">ID</th>
            <th class="border p-2">ID Ensamblar Computadora</th>
            <th class="border p-2">Cantidad</th>
        </tr>
        </thead>
        <tbody>
        <% if (computadoras != null) { %>
        <% for (InventarioComputadoraModelo computadora : computadoras) { %>
        <tr>
            <td class="border p-2"><%= computadora.getId() %></td>
            <td class="border p-2"><%= computadora.getIdEnsamblarComputadora() %></td>
            <td class="border p-2"><%= computadora.getCantidad() %></td>
        </tr>
        <% } %>
        <% } %>
        </tbody>
    </table>

    <!-- Alerta de piezas agotadas -->
    <h2 class="text-xl font-semibold mt-6 text-red-500">Piezas Agotadas o a Punto de Agotarse</h2>
    <ul>
        <% if (piezas != null) { %>
        <% for (InventarioPiezaModelo pieza : piezas) { %>
        <% if (pieza.getCantidad() <= 5) { %>
        <li class="text-red-600">Pieza ID <%= pieza.getIdPieza() %> - Cantidad: <%= pieza.getCantidad() %></li>
        <% } %>
        <% } %>
        <% } %>
    </ul>
</div>
<div class="max-w-4xl mx-auto bg-white p-6 rounded-lg shadow-lg">
    <h2 class="text-2xl font-bold mb-4">Crear Computadora</h2>
    <form action="SvEnsamble" method="post" class="space-y-4">
        <!-- Campos del formulario -->
    </form>
</div>

<script>
    function actualizarCosto() {
        let selects = document.querySelectorAll("select[name^='pieza']");
        let total = 0;
        selects.forEach(select => {
            let costo = select.options[select.selectedIndex].dataset.costo || 0;
            total += parseFloat(costo);
        });
        document.getElementById("costoTotal").value = total;
    }
</script>
</body>
</html>