<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reporte de Computadora Menos Vendida</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.0/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-6 text-center">Reporte de Computadora Menos Vendida</h1>

    <!-- Información de la Computadora -->
    <div class="bg-white rounded-lg shadow-md p-6 mb-6">
        <h2 class="text-xl font-semibold mb-4">Computadora Menos Vendida</h2>
        <p><strong>Nombre de la Computadora:</strong> ${reporte.nombreComputadora}</p>
        <p><strong>Total de Ventas:</strong> ${reporte.totalVentas}</p>
    </div>

    <!-- Detalles de las Ventas -->
    <div class="bg-white rounded-lg shadow-md overflow-hidden">
        <h2 class="text-xl font-semibold p-6">Detalles de las Ventas</h2>
        <table class="min-w-full">
            <thead class="bg-gray-200">
            <tr>
                <th class="px-4 py-2">ID Venta</th>
                <th class="px-4 py-2">Cliente</th>
                <th class="px-4 py-2">Fecha Venta</th>
                <th class="px-4 py-2">Precio Unitario</th>
                <th class="px-4 py-2">Cantidad</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="venta" items="${reporte.ventas}">
                <tr class="border-b">
                    <td class="px-4 py-2 text-center">${venta.idVenta}</td>
                    <td class="px-4 py-2 text-center">${venta.nombreCliente}</td>
                    <td class="px-4 py-2 text-center">${venta.fechaVenta}</td>
                    <td class="px-4 py-2 text-center">${venta.precioUnitario}</td>
                    <td class="px-4 py-2 text-center">${venta.cantidad}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>