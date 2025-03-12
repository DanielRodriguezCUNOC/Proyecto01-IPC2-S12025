<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="es">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reporte de Compras</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 p-6">

<div class="max-w-4xl mx-auto bg-white p-6 rounded-lg shadow-lg">
    <!-- Mostrar un mensaje si no hay ventas -->
    <c:if test="${empty compras}">
        <p class="text-red-500 text-center">No se encontraron compras para el cliente en el rango de fechas
            especificado.</p>
    </c:if>

    <!-- Tabla para mostrar las ventas -->
    <c:if test="${not empty compras}">
        <table class="w-full bg-white rounded-lg shadow-lg mt-4">
            <thead class="bg-gray-200">
            <tr>
                <th class="p-3 text-left">ID Venta</th>
                <th class="p-3 text-left">NIT Cliente</th>
                <th class="p-3 text-left">Vendedor</th>
                <th class="p-3 text-left">Fecha</th>
                <th class="p-3 text-left">Total</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="compra" items="${compras}">
                <tr class="border-t hover:bg-gray-100">
                    <td class="p-3">${compra.id}</td>
                    <td class="p-3">${compra.idCliente}</td>
                    <td class="p-3">${compra.usuario}</td>
                    <td class="p-3">${compra.fechaVenta}</td>
                    <td class="p-3">${compra.totalVenta}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

</body>
</html>

